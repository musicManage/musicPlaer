package com.javaclimb.service;

import com.javaclimb.entity.Singer;
import com.javaclimb.service.impl.SingerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ISingerServiceTest {
    @Autowired
    private ISingerService iSingerService;

    @Test
    public void allSinger(){
        iSingerService.allSinger();
    }

    @Test
    public void singerOfName(){
        iSingerService.singerOfName("炎");
    }

    @Test
    public void addSinger(){
//        iSingerService.insert(new Singer(null,"张三",1,null,null,"地球",null));
    }

    @Test
    public void deleteSinger(){
        iSingerService.delete(21);
    }

    @Test
    public void updateSinger(){
        iSingerService.update(new Singer(22,"张三",0,null,null,"火星",null));
    }


    @Test
    public void selectId(){
        iSingerService.selectByPrimaryKey(1);
    }

    @Test
    public void selectBySex(){
        iSingerService.singerOfSex(1);
    }

    @Test
    public void selectIDByName(){
        iSingerService.nameOfID("坤");
    }
}
