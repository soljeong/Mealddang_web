package com.example.mealddang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.StoreEntity;
import com.example.mealddang.model.repository.StoreRepository;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;
    
    public List<StoreEntity> findAllByStoreName(String StoreName){
        return storeRepository.findAllByStoreName(StoreName);
    }
}