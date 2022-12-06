package com.javaclimb.service;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Collect;

/**
 * <p>
 * 收藏 服务类
 * </p>
 *
 * @author admin
 * @since 2022-12-06
 */
public interface ICollectService {

    /**
     * 添加收藏
     * @param addCollect
     * @return
     */
    public R addCollection(Collect addCollect);

    /**
     * 是否收藏
     * @param isCollect
     * @return
     */
    public R existSongId(Collect isCollect);

    /**
     * 取消收藏
     * @param userId
     * @param songId
     * @return
     */
    public R deleteCollect(Integer userId,Integer songId);

    /**
     * 返回的指定用户 ID 收藏的列表
     * @param userId
     * @return
     */
    public R collectionOfUser(Integer userId);

}
