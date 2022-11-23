package com.javaclimb.controller;

import com.alibaba.fastjson.JSONObject;
import com.javaclimb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
//        System.out.println(name);
        boolean flag = adminService.verifyPassword(name,password);
        if (flag){
            jsonObject.put("code",1);
            jsonObject.put("msg","登录成功");
            session.setAttribute("name",name);
            return jsonObject;
        }
        jsonObject.put("code",0);
        jsonObject.put("msg","用户名或密码错误");
        return jsonObject;
    }

}
