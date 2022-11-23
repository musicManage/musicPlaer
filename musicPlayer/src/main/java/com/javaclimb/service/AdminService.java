package com.javaclimb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.pojo.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface AdminService{
    /**
     * 验证密码是否正确
     */
    public boolean verifyPassword(String username,String password);
}
