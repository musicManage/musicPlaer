package com.javaclimb.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ISongServiceTest {

    @Autowired
    private ISongService iSongService;

    @Test
    public void testSongOfSingerName(){
        iSongService.songOfSingerID(29);
    }
}
