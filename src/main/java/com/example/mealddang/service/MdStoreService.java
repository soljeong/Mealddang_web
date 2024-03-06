package com.example.mealddang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.MdReview;
import com.example.mealddang.model.entity.MdStore;
import com.example.mealddang.model.repository.MdReviewRepository;
import com.example.mealddang.model.repository.MdStoreRepository;

@Service
public class MdStoreService {
    @Autowired
    private MdStoreRepository mdStoreRepository;
    @Autowired
    private MdReviewRepository mdReviewRepository;
    
    public List<MdStore> findAllByStoreName(String searchkey){
        return mdStoreRepository.findAllByStoreName(searchkey);
    }
    public List<MdReview> findReviewsByReview(String searchkey){
        return mdReviewRepository.findReviewsByReview(searchkey);
    }
}