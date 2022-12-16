package com.javaclimb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/getCheckCode")
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/{email}")
    public String getCheckCode(@PathVariable(value = "email") String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1911317393@qq.com");
        message.setTo(email);

        message.setSubject("验证码");
        message.setText("您的验证码为："+checkCode);
        try {
            javaMailSender.send(message);
            System.out.println("发送成功");
        }catch (Exception e){
            return "";
        }
        return checkCode;
    }
}
