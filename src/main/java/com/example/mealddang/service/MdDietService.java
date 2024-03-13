package com.example.mealddang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdDiet;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdDietRepository;

@Service
public class MdDietService {

    @Autowired
    private MdDietRepository mdDietRepository;
    
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
}
