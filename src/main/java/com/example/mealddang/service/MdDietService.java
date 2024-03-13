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
        String gender = mduser.getGender();
        Integer birthyear = Integer.parseInt(mduser.getBirth());
        return mdDietRepository.getDiet(gender, birthyear);
    }
}
