package com.example.mealddang.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mealddang.model.entity.MdDiet;
import com.example.mealddang.model.entity.MdNutResult;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdNutResultRepository;
import com.example.mealddang.service.MdDietService;
import com.example.mealddang.service.MdUserService;

import lombok.extern.slf4j.Slf4j;

import com.example.mealddang.service.MdImgService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

// [인증 후] 식단관리(밀땅일지) 컨트롤러
@Controller
@RequestMapping("/user/diet")
@Slf4j
public class MdDietController {
    @Autowired
    private MdUserService mdUserService;
    @Autowired
    private MdDietService mdDietService;
    @Autowired
    private MdNutResultRepository mdNutResultRepository;
    @Autowired
    private MdImgService mdImgService;

    // 메인
    @GetMapping("/log")
    public String getLog(Model model, Authentication authentication, @RequestParam(value = "startDate", required = false) LocalDate startDate) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        // 유저 나이와 성별에 맞는 섭취기준(MdDiet엔티티) 불러오기
        MdDiet md_diet = mdDietService.getDiet(mdUser);
        model.addAttribute("md_diet", md_diet);

        LocalDate today = LocalDate.now();
        if (startDate == null) {
            startDate = LocalDate.now(); // startDate가 null이면 오늘 날짜로 설정
        }
        today = startDate;

        model.addAttribute("startDate", startDate);
        model.addAttribute("today", today);
        


        // 월요일 가져옴
        LocalDate monday = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        model.addAttribute("monday", monday);
        String mondate = monday.toString();
        model.addAttribute("mondate", mondate);
        
        LocalDate sunday = monday.plusDays(6);
        model.addAttribute("sunday", sunday);


        // 일주일 영양 정보 총합
        List<MdNutResult> weeklyResults = mdNutResultRepository.findByMdUserAndDate(username, monday);
        long totalKcal = 0;
        long totalCarboG = 0;
        long totalProteinG = 0;
        long totalFatG = 0;
        for (MdNutResult weeklyResult : weeklyResults){
            if(weeklyResult.getCreatedDate()!=null && !weeklyResult.getCreatedDate().isBefore(monday) && !weeklyResult.getCreatedDate().isAfter(monday.plusDays(6))) {
                Float kcal = weeklyResult.getKcal() != null ? weeklyResult.getKcal() : 0.0f;
                totalKcal += kcal;
                totalCarboG += weeklyResult.getCarboG() != null ? weeklyResult.getCarboG() : 0.0f;
                totalProteinG += weeklyResult.getProteinG() != null ? weeklyResult.getProteinG() : 0.0f;
                totalFatG += weeklyResult.getFatG() != null ? weeklyResult.getFatG() : 0.0f;
            }
        }
        model.addAttribute("totalKcal", totalKcal);
        model.addAttribute("totalCarboG", totalCarboG);
        model.addAttribute("totalProteinG", totalProteinG);
        model.addAttribute("totalFatG", totalFatG);

        // 일주일 각 날짜 결과 저장
        Map<LocalDate, List<MdNutResult>> eachResultsMap = new HashMap<>();
        // 월요일부터 일요일까지의 각 날짜에 대한 결과를 가져와 Map에 저장
        for (LocalDate date = monday; !date.isAfter(sunday); date = date.plusDays(1)) {
            List<MdNutResult> eachResults = mdNutResultRepository.findByMdUserAndtoDate(username, date);
            eachResultsMap.put(date, eachResults);
        }

        for (LocalDate date = monday; !date.isAfter(sunday); date = date.plusDays(1)) {
            List<String> eachPhotoPaths = new ArrayList<>();
            List<MdNutResult> eachResults = eachResultsMap.get(date);

            long eachKcal = 0;
            long eachCarboG = 0;
            long eachProteinG = 0;
            long eachFatG = 0;
            for (MdNutResult eachResult : eachResults) {
                if (eachResult.getCreatedDate() != null && eachResult.getOriginPath() != null) {
                    eachKcal += eachResult.getKcal() != null ? eachResult.getKcal() : 0.0f;
                    eachCarboG += eachResult.getCarboG() != null ? eachResult.getCarboG() : 0.0f;
                    eachProteinG += eachResult.getProteinG() != null ? eachResult.getProteinG() : 0.0f;
                    eachFatG += eachResult.getFatG() != null ? eachResult.getFatG() : 0.0f;
                    eachPhotoPaths.add(eachResult.getOriginPath().getOriginPath());
                }
            }
            // 각 날짜별로 계산된 합계를 모델에 추가
            model.addAttribute(date.getDayOfWeek().toString().toLowerCase() + "Kcal", eachKcal);
            model.addAttribute(date.getDayOfWeek().toString().toLowerCase() + "CarboG", eachCarboG);
            model.addAttribute(date.getDayOfWeek().toString().toLowerCase() + "ProteinG", eachProteinG);
            model.addAttribute(date.getDayOfWeek().toString().toLowerCase() + "FatG", eachFatG);
            model.addAttribute(date.getDayOfWeek().toString().toLowerCase() + "eachPhotoPaths", eachPhotoPaths);
        }

        return "diet/logPage";
    }

    // 상세1 이미지분석페이지
    @GetMapping("/analysis")
    public String getAnalysis(Model model, Authentication authentication, @RequestParam("date") LocalDate selectedDate) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);
        model.addAttribute("selectedDate", selectedDate);

        return "diet/analysisPage";
    }


    
    // 밀땅일지 - 주간레포트
    @GetMapping("/weekly")
    public String getWeeklyReport(Model model, Authentication authentication) {
        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        // 유저 나이와 성별에 맞는 섭취기준(MdDiet엔티티) 불러오기
        MdDiet md_diet = mdDietService.getDiet(mdUser);
        model.addAttribute("md_diet", md_diet);

        // 유저의 일주일치 섭취량 불러오기
        List<Float> user_diet = mdDietService.weekTotal(username);
        model.addAttribute("user_diet", user_diet);

        return "diet/weeklyPage";
    }

    // 이미지분석 성공 결과 user/diet/airesult
    @GetMapping(value = "/airesult")
    public String getAnalysisResult( @RequestParam("originPath") String originPath, Model model, Authentication authentication) {
        // 파라미터 로그 표시
        System.out.println("originPath: " + originPath);


        String username = authentication.getName();
        MdUser mdUser = mdUserService.findByUsername(username);
        model.addAttribute("mdUser", mdUser);

        // 최근 분석 결과 가져오기 [삭제 예정]
        MdNutResult _mdNutResult = mdImgService.findRecentResultByUsername(username);
        model.addAttribute("mdNutResult", _mdNutResult);

        // origin_path로 분석결과 리스트 가져오기
        List<MdNutResult> allNResults = mdImgService.findAllNResultByOriPath(username, originPath);
        model.addAttribute("allNResults", allNResults);
        log.info("allNResults: "+ allNResults.toString());

        return "diet/resultPage";
    }

    @GetMapping("/user/diet/nutrition")
    public List<Object[]> getNutritionData(@RequestParam("username") String username, @RequestParam("dayofweek") int dayofweek) {
        return mdNutResultRepository.sumNutDaily(username, dayofweek);
    }
}
