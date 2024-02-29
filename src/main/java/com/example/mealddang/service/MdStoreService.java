package com.example.mealddang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdStore;
import com.example.mealddang.model.repository.MdStoreRepository;

@Service
public class MdStoreService {
    @Autowired
    private MdStoreRepository mdStoreRepository;
    
    public List<MdStore> findAllByStoreName(String StoreName){
        return mdStoreRepository.findAllByStoreName(StoreName);
    }
}