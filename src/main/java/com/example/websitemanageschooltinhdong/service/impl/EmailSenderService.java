package com.example.websitemanageschooltinhdong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    JavaMailSender javaMailSender;
    public boolean sendEmail(String toEmail,
                          String subject,String code){
try {
        String body =" mã code quên mật khẩu của bạn là ";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nguyenuyen552000@gmail.com");
        message.setTo(toEmail);
        message.setText(code+body);
        message.setSubject(subject);
        javaMailSender.send(message);

        System.out.println("gửi gmail thành công ");
        return true;
    } catch (Exception e) {
        return false;
    }

    }
}
