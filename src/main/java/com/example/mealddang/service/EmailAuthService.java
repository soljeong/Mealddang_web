package com.example.mealddang.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.mealddang.model.entity.UserEntity;
import com.example.mealddang.model.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthService {
    @Autowired
    private UserRepository userRepository;

    private final JavaMailSender javaMailSender;
    private static final String senderEmail= "${spring.mail.username}";
    private static int number;

    // 이메일 중복 체크
    private void checkEmailExist(String emailAddr) {
        Optional<UserEntity> user = userRepository.findByEmail(emailAddr);
        if (user.isPresent()) {
            log.debug("[MemberServiceImpl.checkEmailExist] Exception : {} Already Exist", emailAddr);
            throw new RuntimeException("Already Exist: " + emailAddr);
        }
    }
    
    // 임의의 인증번호 생성
    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    // 인증용 메일 생성
    public MimeMessage CreateMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("밀땅 회원가입을 위한 인증번호");
            String body = "";
            body += "<h3>" + "밀땅 회원가입을 위한 인증번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }

    public int sendMail(String mail){
        MimeMessage message = CreateMail(mail);
        javaMailSender.send(message);
        return number;
    }
}
