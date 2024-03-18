package com.example.mealddang.config.handler;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdImgUpload;

import lombok.extern.slf4j.Slf4j;

@Slf4j @Component
public class FileHandler {

    public MdImgUpload parseFileInfo(String filePath, String username, MultipartFile multipartFile) throws Exception {

        MdImgUpload mdImgUpload = new MdImgUpload();

        if (multipartFile.isEmpty()) {
            log.info("no file error");
            return mdImgUpload;
        }
        else {
            // 파일명은 랜덤 UUID로 설정
            UUID uuid = UUID.randomUUID();

            // project dir 경로: c:/Users/3149n/workspace/mealddang/
            String projectPath = new File("").getAbsolutePath() + "/";
            log.info("프로젝트 폴더 경로는 " + projectPath);
            
            // resources dir 경로: src/main/resources/static
            String resourcesPath = "src/main/resources/uploaded/";
            log.info("리소스 폴더 경로는 " + resourcesPath);

            // static dir 아래 경로: [username]/[UUID]/
            String underStaticPath = username + "/" + uuid + "/";
            log.info("스태틱 폴더 아래 경로는 " + underStaticPath);

            // 업로드이미지 저장 경로 생성 : src/main/resources/static/[username]/[UUID]/
            String uploadSavingPath = resourcesPath + underStaticPath;
            log.info("업로드이미지가 저장되는 경로는 " + uploadSavingPath);
            File file = new File(uploadSavingPath);
            if (!file.exists()) {
                // mkdirs() : 상위 디렉토리가 존재하지 않는 경우 그것까지 생성
                file.mkdirs();
            }

            // jpeg, png, gif 파일들만 받아서 처리할 예정
            String contentType = multipartFile.getContentType();
            String originalFileExtension;
            // 확장자 없는 파일은 다루지 않음
            if (ObjectUtils.isEmpty(contentType)) {
                log.info("no contentType error");
                return mdImgUpload;
            } else {
                if (contentType.contains("jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("png")) {
                    originalFileExtension = ".png";
                } else if (contentType.contains("gif")) {
                    originalFileExtension = ".gif";
                } else {
                    log.info("incompatible error");
                    return mdImgUpload;
                }
            }

            String new_file_name = uuid + originalFileExtension;
            mdImgUpload = MdImgUpload.builder()
                    .originName(multipartFile.getOriginalFilename())
                    .originPath(underStaticPath + new_file_name)
                    .fileSize(multipartFile.getSize())
                    .build();
            log.info("업로드 된 이미지 엔티티는 " + mdImgUpload.toString());
            
            // full saving path c:/Users/3149n/workspace/mealddang/src/main/resources/static/[username]/[UUID]/[UUID].jpg
            file = new File(projectPath + resourcesPath + underStaticPath + new_file_name);
            multipartFile.transferTo(file);
            log.info("multipartFile : "+multipartFile.toString());
            return mdImgUpload;
        }
    }
}
