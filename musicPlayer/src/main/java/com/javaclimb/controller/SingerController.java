package com.javaclimb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Singer;
import com.javaclimb.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

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
    public R addSinger(Singer singer){
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
    @GetMapping("/{current}/{size}")
    public R allSinger(@PathVariable(value = "current") int current,@PathVariable(value = "size") int size){
        return iSingerService.getByPage(current,size,null);
    }

    /**
     * 根据歌手名字查询
     */
    @PostMapping("/name")
    public Object selectOfName(@RequestParam("name") String name,@RequestParam("current") int current,@RequestParam("size") int size){
        return iSingerService.getByPage(current,size,name);
    }

    /**
     * 根据歌手性别查询
     */
    @GetMapping("/sex/{sex}")
    public R selectOfSex(@PathVariable(value = "sex") Integer sex){
        return iSingerService.singerOfSex(sex);
    }

    /**
     * 更新歌手图片
     */
    @PostMapping("/pic/update/{id}")
    public R updateSinger(@RequestParam("file") MultipartFile picFile, @PathVariable(value = "id") int id){
        return iSingerService.updateOfPic(picFile,id);
    }



}

