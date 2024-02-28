package com.example.mealddang.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mealddang.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    @Query(value = "select * from user_entity where username = :username", nativeQuery = true)
    public UserEntity getUserEntityByUsername(@Param(value = "username") String username);

    public boolean existsByUsername(String username);
}
