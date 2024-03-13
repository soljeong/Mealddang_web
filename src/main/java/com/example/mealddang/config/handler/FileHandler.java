package com.example.mealddang.config.handler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.model.entity.MdImgUpload;

import lombok.extern.slf4j.Slf4j;

@Slf4j @Component
public class FileHandler {

    public MdImgUpload parseFileInfo(String filePath, MultipartFile multipartFile) throws Exception {

        MdImgUpload mdImgUploaded = new MdImgUpload();

        if (multipartFile.isEmpty()) {
            log.info("no file error");
            return mdImgUploaded;
        }
        else {
            // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String current_date = simpleDateFormat.format(new Date());
            
            // project dir 경로: c:/Users/3149n/workspace/mealddang/
            String projectPath = new File("").getAbsolutePath() + "/";
            log.info("프로젝트 폴더 경로는 " + projectPath);
            
            // resources dir 경로: src/main/resources/
            String resourcesPath = "src/main/resources/";
            log.info("리소스 폴더 경로는 " + resourcesPath);

            // static dir 이하 경로: static/uploaded/[오늘날짜 20240306]
            String underStaticPath = "static/uploaded/" + current_date;
            log.info("스태틱 폴더 이하 경로는 " + underStaticPath);

            // uploaded dir 이하 경로 생성 : src/main/resources/static/uploaded/[오늘날짜 20240306]
            String src_current_path = resourcesPath + underStaticPath;
            log.info("이미지 폴더 이하 경로는 " + src_current_path);
            File file = new File(src_current_path);
            if (!file.exists()) {
                // mkdirs() : 상위 디렉토리가 존재하지 않는 경우 그것까지 생성
                file.mkdirs();
            }

            // jpeg, png, gif 파일들만 받아서 처리할 예정
            String contentType = multipartFile.getContentType();
            String originalFileExtension;
            // 확장자 없는 파일은 다루지 않는다
            if (ObjectUtils.isEmpty(contentType)) {
                log.info("no contentType error");
                return mdImgUploaded;
            } else {
                if (contentType.contains("jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("png")) {
                    originalFileExtension = ".png";
                } else if (contentType.contains("gif")) {
                    originalFileExtension = ".gif";
                } else {
                    log.info("incompatible error");
                    return mdImgUploaded;
                }
            }

            // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 이름 지정
            String new_file_name = System.nanoTime() + originalFileExtension;

            mdImgUploaded = MdImgUpload.builder()
                    .imgName(multipartFile.getOriginalFilename())
                    .imgPath(underStaticPath + "/" + new_file_name)
                    .fileSize(multipartFile.getSize())
                    .build();
            log.info("mdImgUploaded : " + mdImgUploaded.toString());
            
            // 저장 경로는 c:/Users/3149n/workspace/mealddang/src/main/resources/static/uploaded/20240306/1661196727851100.png
            file = new File(projectPath + resourcesPath + underStaticPath + "/" + new_file_name);
            // file = new File(absolutePath + path + "/" + new_file_name);
            multipartFile.transferTo(file);
            log.info("multipartFile : "+multipartFile.toString());
            return mdImgUploaded;
        }
    }
}
