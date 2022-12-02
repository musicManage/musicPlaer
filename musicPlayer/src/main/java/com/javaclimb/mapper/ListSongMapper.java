package com.javaclimb.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.javaclimb.entity.ListSong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 歌单包含歌曲列表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
@Mapper
public interface ListSongMapper extends MPJBaseMapper<ListSong> {

}
