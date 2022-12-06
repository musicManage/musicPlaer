package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.ListSong;
import com.javaclimb.entity.Singer;
import com.javaclimb.entity.Song;
import com.javaclimb.entity.SongList;
import com.javaclimb.mapper.ListSongMapper;
import com.javaclimb.service.IListSongService;
import com.javaclimb.vo.SongInListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 歌单包含歌曲列表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
@Service
public class ListSongServiceImpl implements IListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 增加
     *
     * @param listSong
     * @return
     */
    @Override
    public R insert(ListSong listSong) {
        LambdaQueryWrapper<ListSong> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ListSong::getSongId,listSong.getSongId());
        wrapper.eq(ListSong::getSongListId,listSong.getSongListId());
        if (listSongMapper.selectList(wrapper).size()>0){
            return R.error("添加失败");
        }
        if (listSongMapper.insert(listSong) > 0) {
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }

    }

    /**
     * 修改
     *
     * @param listSong
     */
    @Override
    public R update(ListSong listSong) {
        if (listSongMapper.updateById(listSong) > 0) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public R delete(Integer id) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        if (listSongMapper.delete(queryWrapper) > 0) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }


    /**
     * 查询所有歌单里面的歌曲
     */
    @Override
    public R allSongInList(Integer songListId) {
        List<SongInListVo> songInListVos = listSongMapper.selectJoinList(SongInListVo.class,
                new MPJLambdaWrapper<ListSong>()
                        .selectAll(ListSong.class)
                        .selectAs(Song::getName,SongInListVo::getSongName)
                        .selectAs(Song::getIntroduction,SongInListVo::getIntroduction)
                        .selectAs(Singer::getName,SongInListVo::getSingerName)
                        .selectAs(Song::getUrl,SongInListVo::getUrl)
                        .selectAs(Song::getPic,SongInListVo::getPic)
                        .selectAs(Song::getLyric,SongInListVo::getLyric)
                        .selectAs(SongList::getTitle,SongInListVo::getSongListName)
                        .innerJoin(Song.class, Song::getId, ListSong::getSongId)
                        .innerJoin(Singer.class,Singer::getId,Song::getSingerId)
                        .innerJoin(SongList.class, SongList::getId, ListSong::getSongListId)
                        .eq(ListSong::getSongListId,songListId)
        );
        return  R.success(null,songInListVos);
    }

    /**
     * 根据歌单ID和歌名模糊查询所有歌曲
     *
     * @param songListId
     * @param songName
     */
    @Override
    public R listSongOfSongName(Integer songListId, String songName) {
        List<SongInListVo> songInListVos = listSongMapper.selectJoinList(SongInListVo.class,
                new MPJLambdaWrapper<ListSong>()
                        .selectAll(ListSong.class)
                        .selectAs(Song::getName,SongInListVo::getSongName)
                        .selectAs(SongList::getTitle,SongInListVo::getSongListName)
                        .innerJoin(Song.class, Song::getId, ListSong::getSongId)
                        .innerJoin(SongList.class, SongList::getId, ListSong::getSongListId)
                        .eq(ListSong::getSongListId,songListId)
                        .like(Song::getName,songName)
        );
        return  R.success(null,songInListVos);
    }

    /**
     * 根据歌单ID模糊查询所有歌曲
     *
     * @param songListId
     */
    @Override
    public R listSongOfID(Integer songListId) {
        QueryWrapper<ListSong> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("song_list_id",songListId);
        return R.success("查询成功", listSongMapper.selectList(queryWrapper));
    }


}
