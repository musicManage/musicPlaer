package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Collect;
import com.javaclimb.mapper.CollectMapper;
import com.javaclimb.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-12-06
 */
@Service
public class CollectServiceImpl implements ICollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 添加收藏
     *
     * @param addCollect
     * @return
     */
    @Override
    public R addCollection(Collect addCollect) {
        //用type来判断收藏的是歌还是歌单
        if (existSongId(addCollect).getFlag()){
            return R.warning("已经收藏过了");
        }
        if (collectMapper.insert(addCollect) > 0) {
            return R.success("收藏成功", true);
        } else {
            return R.error("收藏失败");
        }
    }

    /**
     * 是否收藏
     *
     * @param isCollect
     * @return
     */
    @Override
    public R existSongId(Collect isCollect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",isCollect.getUserId());
        queryWrapper.eq("song_id",isCollect.getSongId());
        if (collectMapper.selectCount(queryWrapper) > 0) {
            return R.success("已收藏", true);
        } else {
            return R.error("未收藏", false);
        }
    }

    /**
     * 取消收藏
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public R deleteCollect(Integer userId, Integer songId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("song_id",songId);
        if (collectMapper.delete(queryWrapper) > 0) {
            return R.success("取消收藏", false);
        } else {
            return R.error("取消收藏失败");
        }
    }

    /**
     * 返回的指定用户 ID 收藏的列表
     *
     * @param userId
     * @return
     */
    @Override
    public R collectionOfUser(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return R.success("用户收藏", collectMapper.selectList(queryWrapper));
    }
}
