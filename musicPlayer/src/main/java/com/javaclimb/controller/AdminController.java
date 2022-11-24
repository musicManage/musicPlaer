package com.javaclimb.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.controller.util.R;
import com.javaclimb.pojo.Admin;
import com.javaclimb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @PostMapping("/admin/login/status")
    public R loginStatus( Admin admin, HttpSession session){
        return adminService.verifyPassword(admin,session);
    }

}
