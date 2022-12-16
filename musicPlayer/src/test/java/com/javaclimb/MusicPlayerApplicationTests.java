package com.javaclimb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.FileNotFoundException;

@SpringBootTest
class MusicPlayerApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() throws FileNotFoundException {
//        listSongMapper.selectJoinList(SongInListVo.class,
//                new MPJLambdaWrapper<ListSong>()
//                        .selectAll(ListSong.class)
//                        .select(Song::getName)
//                        .innerJoin(Song.class,Song::getId,ListSong::getSongId)
//                        .eq(ListSong::getSongListId,"9"));

    }

}
