package com.javaclimb.controller;

import com.javaclimb.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/getCheckCode")
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/{email}")
    public String getCheckCode(@PathVariable(value = "email") String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "您的注册验证码为："+checkCode;
        try {
            mailService.sendSimpleMail(email, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        return checkCode;
    }
}
