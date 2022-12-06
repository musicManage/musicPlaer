package com.javaclimb.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 收藏
 * </p>
 *
 * @author admin
 * @since 2022-12-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongInCollect{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收藏类型（0歌曲1歌单）
     */
    private Boolean type;

    /**
     * 歌曲id
     */
    private Integer songId;

    /**
     * 歌单id
     */
    private Integer songListId;

    /**
     * 收藏时间
     */
    private LocalDateTime createTime;

    /**
     * 歌名
     */
    private String name;

    /**
     * 歌手
     */
    private String singerName;

    /**
     * 专辑
     */
    private String introduction;

    /**
     * 歌曲图片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lyric;

    /**
     * 歌曲地址
     */
    private String url;

}
