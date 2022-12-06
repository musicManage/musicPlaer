package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Song;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 歌曲 服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
public interface ISongService {

    /**
     * 增加
     *
     * @param song
     * @return
     */
    public R insert(Song song, MultipartFile mpfile);

    /**
     * 修改
     */
    public R update(Song song);

    /**
     * 删除
     */
    public R delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public R selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌手
     */
    public R allSong();

    /**
     * 根据歌名模糊查询列表
     */
    public  R songOfName(String name);

    /**
     * 根据歌名精确查询
     */
    public  R selectSongOfName(String name);

    /**
     * 根据歌手ID模糊查询歌曲
     */
    public  R songOfSingerID(Integer singerID);

    /**
     * 更新歌曲头像
     */
    public R updateOfPic(MultipartFile picFile, int id);

    /**
     * 删除原有歌曲头像
     */
    public R deleteOfPic(String path);

    /**
     * 更新歌曲文件
     */
    public R updateOfFile(MultipartFile urlFile,int id);

    /**
     * 删除原有歌曲文件
     */
    public R deleteOfFile(String path);
}
