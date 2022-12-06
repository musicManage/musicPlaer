package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.ListSong;

/**
 * <p>
 * 歌单包含歌曲列表 服务类
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
public interface IListSongService {

    /**
     * 增加
     *
     * @param listSong
     * @return
     */
    public R insert(ListSong listSong);

    /**
     * 修改
     */
    public R update(ListSong listSong);

    /**
     * 删除
     */
    public R delete(Integer id);

    /**
     * 根据歌单ID模糊查询所有歌曲
     */
    public R listSongOfID(Integer songListId);

    /**
     * 查询所有歌单里面的歌曲
     */
    public R allSongInList(Integer songListId);

    /**
     * 根据歌单ID和歌名模糊查询所有歌曲
     */
    public  R listSongOfSongName(Integer songListId,String songName);
}
