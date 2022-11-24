package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Singer;
import com.javaclimb.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 歌手 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-24
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private ISingerService iSingerService;

    /**
     * 添加歌手
     */
    @PostMapping("/add")
    public R addSinger(@RequestBody Singer singer){
        return iSingerService.insert(singer);
    }

    @PostMapping("/update")
    public R updateSinger(Singer singer){
        return iSingerService.update(singer);
    }

    @GetMapping("/all")
    public R allSinger(){
        return iSingerService.allSinger();
    }

    @GetMapping("/singerName")
    public R selectOfName(String name){
        return iSingerService.singerOfName(name);
    }

    @GetMapping("/singerSex")
    public R selectOfSex(Integer sex){
        return iSingerService.singerOfSex(sex);
    }

}
