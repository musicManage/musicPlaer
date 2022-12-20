package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Admin;
import com.javaclimb.mapper.AdminMapper;
import com.javaclimb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     *
     * @param admin
     * @param session
     */
    @Override
    public R verifyPassword(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",admin.getName());
        queryWrapper.eq("password",admin.getPassword());
        if (adminMapper.selectCount(queryWrapper)>0){
            return R.success("登录成功");
        }
        return R.error("用户名或密码错误");
    }
}
