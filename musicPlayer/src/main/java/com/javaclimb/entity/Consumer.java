package com.javaclimb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端用户
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Consumer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别(1男0女)
     */
    private Boolean sex;

    /**
     * 电话
     */
    private String phoneNum;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 生日
     */
    private LocalDateTime birth;

    /**
     * 个性签名
     */
    private String introduction;

    /**
     * 地区
     */
    private String location;

    /**
     * 头像
     */
    private String avator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
