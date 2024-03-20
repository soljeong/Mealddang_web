package com.example.mealddang.service;
import com.example.mealddang.model.repository.MdNutResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ImagDeleteService {

    private final MdNutResultRepository mdNutResultRepository;

    @Autowired
    public ImagDeleteService(MdNutResultRepository mdNutResultRepository) {
        this.mdNutResultRepository = mdNutResultRepository;

    }
    @Transactional
    public void deleteAllResults() {
      mdNutResultRepository.deleteAll();
    }
}
