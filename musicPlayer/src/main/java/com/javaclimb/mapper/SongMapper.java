package com.javaclimb.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.javaclimb.entity.Song;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 歌曲 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@Mapper
public interface SongMapper extends MPJBaseMapper<Song> {

}
