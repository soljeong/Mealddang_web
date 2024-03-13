package com.example.mealddang.service;

import java.time.LocalDate;
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
import com.example.mealddang.model.entity.MdDietGroup;
import com.example.mealddang.model.entity.MdUser;
import com.example.mealddang.model.repository.MdDietGroupRepository;
import com.example.mealddang.model.repository.MdUserRepository;

@Service
public class MdUserService {
    
    @Autowired
    private MdUserRepository mdUserRepository;
    @Autowired
    private MdDietGroupRepository mdDietGroupRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 이메일로 회원 조회
    public MdUser findByEmail(String p_email){
        Optional<MdUser> mdUser = mdUserRepository.findByEmail(p_email);
        return mdUser.orElseThrow(() -> new NoSuchElementException("MdUser not found with email: " + p_email));
    }
    // 아이디로 회원 조회
    public MdUser findByUsername(String p_username){
        Optional<MdUser> mdUser = mdUserRepository.findByUsername(p_username);
        return mdUser.orElseThrow(() -> new NoSuchElementException("MdUser not found with ID: " + p_username));
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
        // md_diet_group에 회원정보 저장
        MdDietGroup mdDietGroup = new MdDietGroup(p_mdUser);
        mdDietGroupRepository.save(mdDietGroup);
    }
    // 회원 정보 수정
    // 1. 닉네임 수정
    public void modifyNickname(MdUser mdUser, String p_nickname) {
            mdUser.setNickname(p_nickname);
        mdUserRepository.save(mdUser);
    }
    // 2. 생년 수정 -> DietGroup에 바로 반영
    public void modifyBirth(MdUser mdUser, String p_birth) {
        mdUser.setBirth(p_birth);
        mdUserRepository.save(mdUser);
        // DietGroup 엔티티에 반영
        int nowYear = LocalDate.now().getYear();
        int myAge = nowYear - Integer.parseInt(p_birth);
        MdDietGroup mdDietGroup = mdDietGroupRepository.findById(mdUser).get();
        mdDietGroup.setUserAge(myAge);
    }
    // 3. 성별 수정 -> DietGroup에 바로 반영
    public void modifyGender(MdUser mdUser, String p_gender) {
        mdUser.setGender(p_gender);
        mdUserRepository.save(mdUser);
        // DietGroup 엔티티에 반영
        MdDietGroup mdDietGroup = mdDietGroupRepository.findById(mdUser).get();
        mdDietGroup.setUserGender(p_gender);
    }
    // 4. 신장 수정
    public void modifyCm(MdUser mdUser, String p_tall) {
        float tall = Float.parseFloat(p_tall);
        mdUser.setUserCm(tall);
    mdUserRepository.save(mdUser);
    }
    // 5. 몸무게 수정
    public void modifyKg(MdUser mdUser, String p_weight) {
        float weight = Float.parseFloat(p_weight);
        mdUser.setUserKg(weight);
    mdUserRepository.save(mdUser);
    }
    // 5. 관심키워드 수정...0 / 1구현해야함

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
