package com.example.mealddang.service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mealddang.config.constant.Role;
import com.example.mealddang.model.entity.UserEntity;
import com.example.mealddang.model.repository.UserRepository;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 조회 기능
    public UserEntity findByEmail(String email){
        Optional<UserEntity> userentity = userRepository.findByEmail(email);
        return userentity.orElseThrow(() -> new NoSuchElementException("User not found with email: " + email));
}

    // 회원 추가 기능
    public void addUser(UserEntity userEntity){
        userEntity.setRole(Role.USER);
        if(userEntity.getUsername().equals("admin")){
            userEntity.setRole(Role.ADMIN);
        }
        String rawPW = userEntity.getPassword();
        String encPW = bCryptPasswordEncoder.encode(rawPW);
        userEntity.setPassword(encPW);

        userRepository.save(userEntity);
    }

    // 회원 정보 수정 기능
    public void modifyUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    // 회원 삭제 기능
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    // 회원가입 유효성 체크
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        // 유효성 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // 회원아이디 중복 확인
    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(UserEntity entity) {
        boolean usernameDuplicate = userRepository.existsByUsername(entity.getUsername());
        return usernameDuplicate;
    }
}
