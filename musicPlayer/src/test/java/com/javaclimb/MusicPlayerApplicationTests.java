package com.javaclimb;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.javaclimb.entity.ListSong;
import com.javaclimb.entity.Song;
import com.javaclimb.mapper.ListSongMapper;
import com.javaclimb.vo.SongInListVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

@SpringBootTest
class MusicPlayerApplicationTests {

    @Autowired
    ListSongMapper listSongMapper;

    @Test
    void contextLoads() throws FileNotFoundException {
        listSongMapper.selectJoinList(SongInListVo.class,
                new MPJLambdaWrapper<ListSong>()
                        .selectAll(ListSong.class)
                        .select(Song::getName)
                        .innerJoin(Song.class,Song::getId,ListSong::getSongId)
                        .eq(ListSong::getSongListId,"9"));

    }

}
