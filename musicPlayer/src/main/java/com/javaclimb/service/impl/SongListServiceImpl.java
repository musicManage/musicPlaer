package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Singer;
import com.javaclimb.entity.SongList;
import com.javaclimb.mapper.SongListMapper;
import com.javaclimb.service.ISongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌单 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@Service
public class SongListServiceImpl implements ISongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public R insert(SongList songList) {
        String pic = "/img/songListPic/default.jpg";
        songList.setPic(pic);
        if (songListMapper.insert(songList) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public R update(SongList songList) {
        if (songListMapper.updateById(songList)>0){
            return R.success("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public R delete(Integer id) {
        if (songListMapper.deleteById(id)>0){
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public R selectByPrimaryKey(Integer id) {
        return R.success(null,songListMapper.selectById(id));
    }

    /**
     * 查询所有歌单
     */
    @Override
    public R allSongList() {
        return R.success(null,songListMapper.selectList(null));
    }

    /**
     * 根据标题精确查询歌单列表
     *
     * @param title
     */
    @Override
    public R songListOfTitle(String title) {
        return null;
    }

    /**
     * 根据标题模糊查询歌单列表
     *
     * @param title
     */
    @Override
    public R likeTitle(String title) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        return R.success(null, songListMapper.selectList(queryWrapper));
    }

    /**
     * 根据风格模糊查询歌单列表
     *
     * @param style
     */
    @Override
    public R likeStyle(String style) {
        QueryWrapper<SongList> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("style",style);
        return R.success(null, songListMapper.selectList(queryWrapper));
    }
}
