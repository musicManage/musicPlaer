package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Admin;
import org.springframework.web.bind.annotation.RequestBody;


public interface IAdminService {
    /**
     * 验证密码是否正确
     */
    public R verifyPassword(@RequestBody Admin admin);
}
