package com.example.mealddang.service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.mealddang.config.constant.Role;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdUserRepository;


@Service
public class MdUserService {
    
    @Autowired
    private MdUserRepository mdUserRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원 조회
    public MdUser findByEmail(String email){
        Optional<MdUser> mdUser = mdUserRepository.findByEmail(email);
        return mdUser.orElseThrow(() -> new NoSuchElementException("MdUser not found with email: " + email));
}
    // 회원 추가
    public void addMdUser(MdUser p_mdUser){
        p_mdUser.setRole(Role.USER);
        // ID: admin -> 관리자 권한 부여
        if(p_mdUser.getUsername().equals("admin")){
            p_mdUser.setRole(Role.ADMIN);
        }
        // 비밀번호 인코딩 후 저장
        String rawPW = p_mdUser.getPassword();
        String encPW = bCryptPasswordEncoder.encode(rawPW);
        p_mdUser.setPassword(encPW);
        // md_user table에 회원정보 저장
        mdUserRepository.save(p_mdUser);
    }
    // 회원 정보 수정
    public void modifyMdUser(@NonNull MdUser p_mdUser) {
        MdUser mdUser = mdUserRepository.findByUsername(p_mdUser.getUsername())
            .orElseThrow(() -> new NoSuchElementException("No MdUser found with username: " + p_mdUser.getUsername()));
        mdUserRepository.save(mdUser);
    }
    // 회원 삭제
    public void deleteUser(@NonNull String p_username) {
        try {
            mdUserRepository.deleteById(p_username);
        } catch (NullPointerException e) {
            System.err.println("No MdUser found with username: " + p_username);
        }
    }
    // 회원가입 유효성 체크 -> html valid_%s
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
    public boolean checkUsernameDuplication(MdUser mdUser) {
        boolean usernameDuplicate = mdUserRepository.existsByUsername(mdUser.getUsername());
        return usernameDuplicate;
    }
}
