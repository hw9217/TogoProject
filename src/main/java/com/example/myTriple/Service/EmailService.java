package com.example.myTriple.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

//    private final EmailService emailService;
//
//
//    private final JavaMailSender javaMailSender;
//
//    @Async
//    public void sendMail(SimpleMailMessage email) {
//        javaMailSender.send(email);
//    }
}
