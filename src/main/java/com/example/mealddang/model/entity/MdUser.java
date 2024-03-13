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
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @ToString
public class MdUser {
    @Id @Column(name = "user_id", columnDefinition = "varchar(20)")
    @NotBlank(message = "아이디 입력은 필수입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]{3,20}$", message = "아이디는 공백없이 문자와 숫자로만 3자 이상 20자 이내로 입력해주세요.")
    private String username;

    @Column(name = "user_pw", nullable = false)
    @NotBlank(message = "비밀번호 입력은 필수입니다.") @Size(min = 4, message = "비밀번호는 4자 이상 입력해주세요.")
    private String password;

    @Column(name = "user_email", nullable = false)
    @NotBlank(message = "이메일 입력은 필수입니다.") @Email(message = "유효한 이메일 주소를 입력해주세요.")
    private String email;
    
    @Column(name = "user_nickname", columnDefinition = "varchar(15) default 'my_nickname'")
    private String nickname;

    @Column(name = "user_gender", columnDefinition = "char(1) default 'M'")
    private String gender;

    @Column(name = "user_birth", columnDefinition = "char(6) default '1999'")
    private String birth;

    @Column(columnDefinition = "float default 175.0")
    private Float userCm;

    @Column(columnDefinition = "float default 70.0")
    private Float userKg;

    @Column(name = "user_role") @Enumerated(EnumType.STRING)
    private Role role;

    // @Column(columnDefinition = "varchar(5) default '강남구'")
    // private int userArea1;
    // @Column(columnDefinition = "varchar(5) default '종로구'")
    // private int userArea2;
    // @Column(columnDefinition = "varchar(5) default '서초구'")
    // private int userArea3;

    @Column(columnDefinition = "int default 0")
    private int userTag1;
    @Column(columnDefinition = "int default 0")
    private int userTag2;
    @Column(columnDefinition = "int default 0")
    private int userTag3;
    @Column(columnDefinition = "int default 0")
    private int userTag4;
    @Column(columnDefinition = "int default 0")
    private int userTag5;
    @Column(columnDefinition = "int default 0")
    private int userTag6;
    @Column(columnDefinition = "int default 0")
    private int userTag7;
    @Column(columnDefinition = "int default 0")
    private int userTag8;
    @Column(columnDefinition = "int default 0")
    private int userTag9;
    @Column(columnDefinition = "int default 0")
    private int userTag10;

    public MdUser update(String p_username) {
        this.username = p_username;
        return this;
    }
}
