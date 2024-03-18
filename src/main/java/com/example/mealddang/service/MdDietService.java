package com.example.mealddang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdDiet;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdDietRepository;
import com.example.mealddang.model.repository.MdNutResultRepository;

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
}
