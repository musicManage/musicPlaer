package com.javaclimb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.pojo.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 验证密码是否正确
     */
    public int verifyPassword(String username,String password);
}
