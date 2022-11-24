package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.dao.AdminDao;
import com.javaclimb.pojo.Admin;
import com.javaclimb.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

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
        if (adminDao.selectCount(queryWrapper)>0){
            session.setAttribute("name",admin.getName());
            return R.success("登录成功");
        }
        return R.error("用户名或密码错误");
    }
}
