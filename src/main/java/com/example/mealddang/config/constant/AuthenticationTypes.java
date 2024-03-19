package com.example.mealddang.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthenticationTypes {
    BadCredentialsException(401, "비밀번호 불일치"),
    UsernameNotFoundException(402, "계정없음"),
    AccountExpiredException(403, "계정만료"),
    CredentialsExpiredException(404, "비밀번호 만료"),
    DisabledException(405, "계정 비활성화"),
    LockedException(406, "계정 잠김"),
    NoneException(407, "알 수 없는 에러");

    private int code;
    private String msg;
}
