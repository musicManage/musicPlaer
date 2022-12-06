package com.javaclimb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 歌手
 * </p>
 *
 * @author admin
 * @since 2022-11-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("singer")
public class Singer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别(1男0女)
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像
     */
    @TableField("pic")
    private String pic;

    /**
     * 生日
     */
    @TableField("birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    /**
     * 地区
     */
    @TableField("location")
    private String location;

    /**
     * 简介
     */
    @TableField("introduction")
    private String introduction;
}
