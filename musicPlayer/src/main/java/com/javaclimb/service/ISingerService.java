package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Singer;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 歌手 服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-24
 */
public interface ISingerService {

    /**
     * 增加
     *
     * @param singer
     * @return
     */
    public R insert(Singer singer);

    /**
     * 修改
     */
    public R update(Singer singer);

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
    public R allSinger();

    /**
     * 根据歌手名字模糊查询列表
     */
    public  R singerOfName(String name);

    /**
     * 根据歌手性别模糊查询列表
     */
    public  R singerOfSex(Integer sex);

    /**
     * 更新歌手头像
     */
    public R updateOfPic(MultipartFile picFile, int id);

    /**
     * 删除原有歌手头像
     */
    public R deleteOfPic(String path);
}
