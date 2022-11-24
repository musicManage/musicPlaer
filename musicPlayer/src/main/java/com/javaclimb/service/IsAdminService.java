package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Service
public interface IsAdminService {
    /**
     * 验证密码是否正确
     */
    public R verifyPassword(@RequestBody Admin admin, HttpSession session);
}
