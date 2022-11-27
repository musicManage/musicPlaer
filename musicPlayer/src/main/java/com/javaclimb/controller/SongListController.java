package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.SongList;
import com.javaclimb.service.ISongListService;
import com.javaclimb.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 歌单 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private ISongListService iSongListService;

    // 添加歌单
    @PostMapping("/add")
    public R addSongList(SongList songList) {
        return iSongListService.insert(songList);
    }

    // 删除歌单
    @GetMapping("/delete")
    public R deleteSongList(@RequestParam(value = "id") int id) {
        return iSongListService.delete(id);
    }

    // 返回所有歌单
    @GetMapping("/")
    public R allSongList() {
        return iSongListService.allSongList();
    }

    // 返回标题包含文字的歌单
    @GetMapping("/likeTitle/detail")
    public R songListOfLikeTitle(@RequestParam String title) {
        return iSongListService.likeTitle('%' + title + '%');
    }

    // 返回指定类型的歌单
    @GetMapping("/style/detail")
    public R songListOfStyle(@RequestParam String style) {
        return iSongListService.likeStyle('%' + style + '%');
    }

    // 更新歌单信息
    @PostMapping("/update")
    public R updateSongListMsg(SongList songList) {
        return iSongListService.update(songList);

    }

    // 更新歌单图片
    @PostMapping("/update/pic")
    public R updateSongListPic(@RequestParam("file") MultipartFile mpFile, @RequestParam(value = "id") int id) {
        return iSongListService.updateSongListImg(mpFile,id);
    }

}
