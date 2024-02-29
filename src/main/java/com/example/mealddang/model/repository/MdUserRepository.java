package com.example.mealddang.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mealddang.model.entity.MdUser;

public interface MdUserRepository extends JpaRepository<MdUser, String> {

    // 이메일로 유저엔티티 찾기 -> 결과가 있을 수도 없을 수도(Optional)
    public Optional<MdUser> findByEmail(String email);
    // 아이디로 유저엔티티 찾기 -> 결과가 있을 수도 없을 수도
    public Optional<MdUser> findByUsername(String username);

    public boolean existsByUsername(String username);
}
