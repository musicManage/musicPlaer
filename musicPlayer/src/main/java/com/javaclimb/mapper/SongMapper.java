package com.javaclimb.mapper;

import com.javaclimb.entity.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SongMapper extends BaseMapper<Song> {

}
