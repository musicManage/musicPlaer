package com.javaclimb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.controller.util.R;
import com.javaclimb.pojo.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Service
public interface AdminService{
    /**
     * 验证密码是否正确
     */
    public R verifyPassword(@RequestBody Admin admin, HttpSession session);
}
