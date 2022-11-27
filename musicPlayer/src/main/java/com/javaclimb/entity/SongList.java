package com.javaclimb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 歌单
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("song_list")
public class SongList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 歌单图片
     */
    private String pic;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 风格
     */
    private String style;
}
