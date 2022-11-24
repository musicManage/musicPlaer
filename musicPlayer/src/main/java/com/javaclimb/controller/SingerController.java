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

    /**
     * 更新歌手信息
     */
    @PostMapping("/update")
    public R updateSinger(Singer singer){
        return iSingerService.update(singer);
    }

    /**
     * 返回所有歌手信息
     */
    @GetMapping("/all")
    public R allSinger(){
        return iSingerService.allSinger();
    }

    /**
     * 根据歌手名字查询
     */
    @GetMapping("/singerName/{name}")
    public R selectOfName(@PathVariable(value = "name") String name){
        return iSingerService.singerOfName(name);
    }

    /**
     * 根据歌手性别查询
     */
    @GetMapping("/singerSex/{sex}")
    public R selectOfSex(@PathVariable(value = "sex") Integer sex){
        return iSingerService.singerOfSex(sex);
    }

}
