package com.javaclimb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin")
public class Admin implements Serializable {
    @TableId
    private Integer id;
    private String name;
    private String password;
}
