package com.javaclimb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao extends BaseMapper<Admin> {
}
