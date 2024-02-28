package com.example.mealddang.model.entity;

import com.example.mealddang.config.constant.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @Column(columnDefinition = "varchar(20)")
    @NotBlank(message = "아이디 입력은 필수입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,20}$", message = "아이디는 공백없이 문자와 숫자로만 3자 이상 20자 이내로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Size(min = 4, message = "비밀번호는 4자 이상 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;
    
    @Column(columnDefinition = "varchar(20) default 'my_nickname'")
    private String nickname;

    @Column(columnDefinition = "varchar(10) default 'Male'")
    private String gender;

    @Column(columnDefinition = "varchar(10) default '991212'")
    private String birth;

    @Column(columnDefinition = "float default 175.0")
    private Float length;

    @Column(columnDefinition = "float default 70.0")
    private Float weight;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity update(String username) {
        this.username = username;
        return this;
    }
}
