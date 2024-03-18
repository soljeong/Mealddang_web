package com.example.mealddang.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.config.handler.FileHandler;
import com.example.mealddang.model.entity.MdYoloResult;
import com.example.mealddang.model.entity.YoloResponse;
import com.example.mealddang.model.entity.MdImgUpload;
import com.example.mealddang.model.entity.MdNutInfo;
import com.example.mealddang.model.entity.MdNutResult;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdYoloResultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import com.example.mealddang.model.repository.MdImgUploadRepository;
import com.example.mealddang.model.repository.MdNutInfoRepository;
import com.example.mealddang.model.repository.MdNutResultRepository;
import com.example.mealddang.model.repository.MdUserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service @Slf4j
public class MdImgService {

    @Autowired
    private MdUserRepository mdUserRepository;
    @Autowired
    private MdImgUploadRepository mdImgUploadRepository;
    @Autowired
    private MdNutResultRepository mdNutResultRepository;
    @Autowired
    private MdYoloResultRepository mdYoloResultRepository;
    @Autowired
    private MdNutInfoRepository mdNutInfoRepository;

    private final FileHandler fileHandler;
    
    // 생성자
    @Autowired
    public MdImgService(MdImgUploadRepository p_uploadRepository) {
        this.mdImgUploadRepository = p_uploadRepository;
        this.fileHandler = new FileHandler();
    }

    // 이미지업로더
    public MdImgUpload imgUploader(MdImgUpload p_upload, String p_username,  MultipartFile imgFile, LocalDate selectedDate) throws Exception {
        MdImgUpload mdImgUpload = fileHandler.parseFileInfo(p_upload.getOriginPath(), p_username, imgFile);
        mdImgUpload.setCreatedDate(selectedDate);
        System.out.println(mdImgUpload.getCreatedDate());
        // DB에 저장
        mdImgUploadRepository.save(mdImgUpload);
        return mdImgUpload;
    }

    // MdNutResult 저장
    public MdNutResult saveNutResult(String p_username, String p_oripath, List<String> yoloResult) {
        MdUser mdUser = mdUserRepository.findByUsername(p_username).get();
        MdImgUpload mdImgUpload = mdImgUploadRepository.findUploadEntitybyPath(p_oripath);
        MdNutResult mdNutResult = new MdNutResult();
        
        for (String result : yoloResult) {
            mdNutResult.setUsername(mdUser);
            mdNutResult.setOriginPath(mdImgUpload);
            mdNutResult.setResultPath(result);

            String[] labelArr = result.split("_");
            MdNutInfo resultInfo = getResultLabel(labelArr[1]);
            mdNutResult.setResultLabel(resultInfo.getFoodName());

            mdNutResult.setCarboG(resultInfo.getCarbohydrate());
            mdNutResult.setFatG(resultInfo.getFat());
            mdNutResult.setKcal(resultInfo.getEnergyKcal());
            mdNutResult.setProteinG(resultInfo.getProtein());
            mdNutResultRepository.save(mdNutResult);
        }


        return mdNutResult;
    }

    public MdNutInfo getResultLabel(String yoloLabel) {
        return mdNutInfoRepository.findByClassLabel(yoloLabel);
    }

    // MdYoloResult 저장
    public MdYoloResult saveYoloResult(String p_originPath, List<String> yoloResult) {
        MdYoloResult mdYoloResult = new MdYoloResult();

        for (String resultPath : yoloResult) {
            mdYoloResult.setOriginPath(p_originPath);
            mdYoloResult.setResultPath(resultPath);
            mdYoloResultRepository.save(mdYoloResult);
        }
        return mdYoloResult;
    }

    public List<String> sendYolo(String p_originPath) {
        List<String> yoloResult = new ArrayList<>();

        // 현재 작업 디렉토리 확인
        String currentDirectory = System.getProperty("user.dir");
        String scrDirectory = "/src/main/resources/";
        String fullPath = currentDirectory + scrDirectory;
        System.out.println("fullPath: " + fullPath);
        String uploadUrl = "http://3.38.213.167:8000/predict"; // FastAPI 업로드 엔드포인트 URL
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(uploadUrl);
            File imageFile = new File(fullPath, p_originPath); // 현재 디렉토리와 상대 경로를 조합하여 파일 경로 생성
            HttpEntity multipartEntity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", imageFile, ContentType.DEFAULT_BINARY, imageFile.getName()) // "file"이름으로 변경
                    .build();
            httpPost.setEntity(multipartEntity);
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("successfully Request Yolo");
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity);
                String cleanedJsonString = responseString.replaceAll("\\\\", "").replaceAll("^\"|\"$", "");
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // JSON 문자열을 객체로 변환
                    YoloResponse yoloResponse = objectMapper.readValue(cleanedJsonString, YoloResponse.class);
                    String[] images = yoloResponse.getImages();
                    for (String image : images) {
                        System.out.println("Image: " + image);
                        yoloResult.add(image);
                    }
                } catch (Exception e) {
                    System.out.println("Failed to parse JSON: " + e.getMessage());
                }
            } else {
                System.out.println("Fail Request Yolo. Status code: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return yoloResult;
    }

    public List<String> getYoloImg(List<String> imgPathList, String originImgPath) {
        List<String> imgFullPathList = new ArrayList<>();

        // 현재 작업 디렉토리 확인
        String currentDirectory = System.getProperty("user.dir");
        String[] pathArr = originImgPath.replace("//", "/").split("/");
        String resultPath = "";
        for (int i = 0; i < pathArr.length-1; i++) {
            resultPath += pathArr[i] + "/";
        }
        String scrDirectory = "/src/main/resources/" + resultPath;

        String fullPath = currentDirectory + scrDirectory;
        System.out.println("fullPath =====> " + fullPath);
        for (String imgPath : imgPathList) {
            String imageUrl = "http://3.38.213.167:8000/download/" + imgPath; // FastAPI 서버에서 이미지를 다운로드할 URL
            // String[] imgArr = imgPath.split(".");
            String savePath = fullPath + imgPath; // 이미지를 저장할 경로
            System.out.println(savePath);
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(imageUrl);
                HttpResponse response = httpClient.execute(httpGet);
                InputStream inputStream = response.getEntity().getContent();
                System.out.println(inputStream);
    
                // 이미지 저장
                try (FileOutputStream outputStream = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    imgFullPathList.add(resultPath + imgPath);
                    System.out.println("Image downloaded successfully.");
                } catch (IOException e) {
                    System.err.println("Failed to save image: " + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("Failed to download image: " + e.getMessage());
            }
        }

        return imgFullPathList;
    }

    // 회원ID로 해당 회원의 모든 업로드이미지원본 엔티티 찾기
    public List<MdImgUpload> findAllUploadbyUsername(String username) {
        log.info(username + " 회원님의 모든 업로드 히스토리를 찾고 있습니다.");
        List<String> imgPaths = mdNutResultRepository.findAllPathbyUsername(username);
        log.info(imgPaths.size() + "개의 업로드 히스토리를 찾았습니다.");
        return mdImgUploadRepository.findAllImgbyPaths(imgPaths);
    }

    // 회원ID로 해당 회원의 모든 이미지분석결과 엔티티 찾기
    public List<MdNutResult> findAllNutResultbyUsername(MdUser username) {
        log.info(username + " 회원님의 모든 이미지 분석 히스토리를 찾고 있습니다.");
        List<MdNutResult> mdNutResults = mdNutResultRepository.findAllNutResultbyUsername(username);
        log.info(mdNutResults.size() + "개의 이미지 분석 히스토리를 찾았습니다.");
        return mdNutResults;
    }

    // originPath로 이미지 삭제하기_보류
}
