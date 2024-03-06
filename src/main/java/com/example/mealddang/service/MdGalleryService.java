package com.example.mealddang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mealddang.config.handler.FileHandler;
import com.example.mealddang.model.entity.MdGallery;
import com.example.mealddang.model.repository.MdGalleryRepository;

@Service
public class MdGalleryService {
    
    private final MdGalleryRepository mdGalleryRepository;
    private final FileHandler fileHandler;

    public MdGalleryService(MdGalleryRepository mdGalleryRepository) {
        this.mdGalleryRepository = mdGalleryRepository;
        this.fileHandler = new FileHandler();
    }

    public MdGallery addMdGallery(MdGallery gallery, MultipartFile imgFile) throws Exception {

        // 파일을 저장하고 그 gallery 객체를 가지고 있는다
        MdGallery mdGallery = fileHandler.parseFileInfo(gallery.getId(), imgFile);
        mdGalleryRepository.save(mdGallery);
        return mdGallery;
    }

    public List<MdGallery> findBoards() {
        return mdGalleryRepository.findAll();
    }

    public Optional<MdGallery> findBoard(Long id) {
        return mdGalleryRepository.findById(id);
    }
}
