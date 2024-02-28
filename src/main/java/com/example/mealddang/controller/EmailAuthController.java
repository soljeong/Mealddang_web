package com.example.mealddang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mealddang.service.EmailAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailAuthController {
    @Autowired
    EmailAuthService emailAuthService;

    @ResponseBody
    @PostMapping("/emailauth/request")
    public String emailAuthRequest(String emailAddr) {
        int number = emailAuthService.sendMail(emailAddr);
        String num = "" + number;
        return num;
    }
}
