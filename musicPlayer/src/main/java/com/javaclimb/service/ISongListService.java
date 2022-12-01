package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Singer;
import com.javaclimb.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 歌单 服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
public interface ISongListService {

    /**
     * 增加
     *
     * @param
     * @return
     */
    public R insert(SongList songList);

    /**
     * 修改
     */
    public R update(SongList songList);

    /**
     * 删除
     */
    public R delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public R selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     */
    public R allSongList();

    /**
     * 根据标题精确查询歌单列表
     */
    public  R songListOfTitle(String title);

    /**
     * 根据标题模糊查询歌单列表
     */
    public  R likeTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     */
    public  R likeStyle(String style);

    /**
     * 更新歌单图片
     */
    public R updateSongListImg(MultipartFile mpFile, int id);

    /**
     * 删除原有歌单图片
     * */
    public R deleteOfPic(String path);



}
