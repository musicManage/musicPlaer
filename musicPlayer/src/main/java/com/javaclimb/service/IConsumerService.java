package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Consumer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javaclimb.entity.Singer;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端用户 服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
public interface IConsumerService {

    /**
     * 增加
     *
     * @param consumer
     * @return
     */
    public R insert(Consumer consumer);

    /**
     * 修改
     */
    public R update(Consumer consumer);

    /**
     * 删除
     */
    public R delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public R selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    public R allConsumer();

    /**
     * 根据用户名字模糊查询列表
     */
    public  R consumerOfName(String name);

    /**
     * 检测用户名是否重复
     */
    public  R verifyOfName(String name);

    /**
     * 根据用户性别模糊查询列表
     */
    public  R consumerOfSex(Integer sex);

    /**
     * 更新用户头像
     */
    public R updateOfPic(MultipartFile picFile, int id);

    /**
     * 删除原有用户头像
     */
    public R deleteOfPic(String path);

}
