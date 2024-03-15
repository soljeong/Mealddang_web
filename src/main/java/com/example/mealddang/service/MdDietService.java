package com.example.mealddang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdDiet;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdDietRepository;
import com.example.mealddang.model.repository.MdNutResultRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;


@Service
public class MdDietService {

    @Autowired
    private MdDietRepository mdDietRepository;
    @Autowired
    private MdNutResultRepository mdNutResultRepository;
    
    // 회원 개인 건강 정보 조회하는 메소드
    public MdDiet getDiet(MdUser mduser) {

        String gender;
        // mduser.getGender()가 null 또는 빈 문자열인지 확인
        if (mduser.getGender() != null && !mduser.getGender().isEmpty()) {
            gender = mduser.getGender();
        } else {
            // mduser.getGender()가 null일 때의 처리
            gender = "M";
        }

        Integer birthyear;
        // mduser.getBirth()가 null 또는 빈 문자열인지 확인
        if (mduser.getBirth() != null && !mduser.getBirth().isEmpty()) {
            try {
                birthyear = Integer.parseInt(mduser.getBirth());
            } catch (NumberFormatException e) {
                // 숫자로 변환할 수 없을 때의 예외 처리
                birthyear = 1999;
            }
        } else {
            // mduser.getBirth()가 null일 때의 처리
            birthyear = 1999;
        }
        
        return mdDietRepository.getDiet(gender, birthyear);
    }

    // 회원의 이번주 월~일 섭취량(열량, 탄, 단, 지 순서) 조회하는 메소드
    public List<List<Object[]>> sumNutDaily(String username) {
        List<List<Object[]>> weekNutList = new ArrayList<>();
        for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
            List<Object[]> dailyNutList = mdNutResultRepository.sumNutDaily(username, dayOfWeek);
            weekNutList.add(dailyNutList);
        }
        return weekNutList;
    }


    public LocalDate getWeekDatesFromMonday() {
        LocalDate monday = LocalDate.now();
        DayOfWeek dayOfWeek = monday.getDayOfWeek();
        // 현재 요일이 월요일이 아니라면, 이번 주 월요일로 이동
        if (!dayOfWeek.equals(DayOfWeek.MONDAY)) {
            monday = monday.minusDays(dayOfWeek.getValue() - 1);
        }
        
        return monday;
    }

    public LocalDate getWeekDatesFromtoday() {
        LocalDate today = LocalDate.now();
        return today;
    }

    public List<List<Float>> getWeeklySumNutrition(String username) {

        List<List<Float>> weeklySumNutritionList = new ArrayList<>();

        for (int i = 1; i <= 7; i++) { // 7일치 데이터를 요청
            List<Object[]> resultRows = mdNutResultRepository.sumNutDaily(username, i);
            List<Float> weeklySumNutrition = new ArrayList<>();

            if (resultRows != null && !resultRows.isEmpty()) {
                Object[] row = resultRows.get(0); // Assuming only one row is returned
                for (Object obj : row) {
                    if (obj != null) {
                        weeklySumNutrition.add(Float.valueOf(obj.toString()));
                    } else {
                        weeklySumNutrition.add(0f); // If any value is null, adding 0
                    }
                }
            } else {
                // If no row is returned, adding 0 for all columns
                for (int j = 0; j < 3; j++) {
                    weeklySumNutrition.add(0f);
                }
            }
            weeklySumNutritionList.add(weeklySumNutrition);
        }
        return weeklySumNutritionList;
    }
}
