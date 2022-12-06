package com.javaclimb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.entity.SongList;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 歌单 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@Mapper
public interface SongListMapper extends BaseMapper<SongList> {

}
