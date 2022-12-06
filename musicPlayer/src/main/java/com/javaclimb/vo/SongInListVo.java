package com.javaclimb.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongInListVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌曲id
     */
    private Integer songId;

    /**
     * 歌名
     */
    private String songName;

    /**
     * 歌手名
     */
    private String singerName;

    /**
     * 歌曲文件
     */
    private String url;

    /**
     * 歌曲图片
     */
    private String pic;

    /**
     * 歌词
     */
    private String lyric;

    /**
     * 歌单id
     */
    private Integer songListId;

    /**
     * 歌单名
     */
    private String songListName;

    /**
     * 歌曲专辑
     */
    private String introduction;

}
