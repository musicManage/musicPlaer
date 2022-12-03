package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Song;
import com.javaclimb.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 歌曲 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private ISongService iSongService;

    /**
     * 添加歌曲
     */
    @PostMapping("/add")
    public R addSong(Song song,@RequestParam("file")MultipartFile mpFile){
        return iSongService.insert(song,mpFile);
    }

    /**
     * 更新歌曲信息
     */
    @PostMapping("/update")
    public R updateSong(Song song){
        return iSongService.update(song);
    }

    /**
     * 删除歌曲信息
     */
    @PostMapping("/delete")
    public R deleteSong(@RequestParam(value = "id") String id){
        Integer integer = Integer.valueOf(id);
        return iSongService.delete(integer);
    }

    /**
     * 返回所有歌曲信息
     */
    @GetMapping("/")
    public R allSong(){
        return iSongService.allSong();
    }

    /**
     * 根据歌名查询
     */
    @GetMapping("/name/{name}")
    public Object selectOfName(@PathVariable(value = "name") String name){
        return iSongService.songOfName(name).getData();
    }

    /**
     * 根据歌手精确查询
     */
    @PostMapping("/select/{name}")
    public Object selectPOfName(@PathVariable(value = "name") String name){
        return iSongService.selectSongOfName(name).getData();
    }

    /**
     * 根据歌手ID查询
     */
    @GetMapping("/singer/{singerId}")
    public R selectOfSId(@PathVariable(value = "singerId") Integer singerId){
        return iSongService.songOfSingerID(singerId);
    }

    /**
     * 根据歌曲ID查询
     */
    @GetMapping("/key/{id}")
    public R selectOfPrimary(@PathVariable(value = "id") Integer songId){
        return iSongService.selectByPrimaryKey(songId);
    }

    /**
     * 更新歌曲图片
     */
    @PostMapping("/pic/update/{id}")
    public R updateSong(@RequestParam("file") MultipartFile picFile, @PathVariable(value = "id") int id){
        return iSongService.updateOfPic(picFile,id);
    }

    /**
     * 更新歌曲文件
     */
    @PostMapping("/update/file/{id}")
    public R updateSongFile(@RequestParam("file") MultipartFile urlFile, @PathVariable(value = "id") int id){
        return iSongService.updateOfFile(urlFile, id);
    }
}
