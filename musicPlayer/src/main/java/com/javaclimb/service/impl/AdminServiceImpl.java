package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.mapper.AdminMapper;
import com.javaclimb.entity.Admin;
import com.javaclimb.service.IsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements IsAdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     *
     * @param admin
     * @param session
     */
    @Override
    public R verifyPassword(Admin admin, HttpSession session) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",admin.getName());
        queryWrapper.eq("password",admin.getPassword());
        if (adminMapper.selectCount(queryWrapper)>0){
            session.setAttribute("name",admin.getName());
            return R.success("登录成功");
        }
        return R.error("用户名或密码错误");
    }
}
