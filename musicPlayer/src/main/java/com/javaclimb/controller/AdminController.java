package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Admin;
import com.javaclimb.service.IsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
public class AdminController {

    @Autowired
    private IsAdminService isAdminService;

    /**
     * 判断是否登录成功
     */
    @PostMapping("/admin/login/status")
    public R loginStatus( Admin admin, HttpSession session){
        return isAdminService.verifyPassword(admin,session);
    }

}
