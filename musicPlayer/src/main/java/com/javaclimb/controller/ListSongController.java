package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.ListSong;
import com.javaclimb.service.IListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 歌单包含歌曲列表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private IListSongService iListSongService;

    // 给歌单添加歌曲
    @PostMapping("/add")
    public R addListSong(ListSong listSong) {
        return iListSongService.insert(listSong);
    }

    // 删除歌单里的歌曲
    @PostMapping("/delete")
    public R deleteListSong(@RequestParam(value = "id") int id) {
        return iListSongService.delete(id);
    }

    // 返回歌单里指定歌单 ID 的歌曲
    @GetMapping("/{id}")
    public Object listSongOfSongId(@PathVariable(value = "id") int songListId) {
        return iListSongService.allSongInList(songListId).getData();
    }

    @GetMapping("/{id}/{songName}")
    public Object listSongOfSongId(@PathVariable(value = "id") int songListId,@PathVariable(value = "songName") String songName) {
        return iListSongService.listSongOfSongName(songListId,songName).getData();
    }

    // 更新歌单里面的歌曲信息
    @PostMapping("/update")
    public R updateListSongMsg(ListSong listSong) {
        return iListSongService.update(listSong);
    }

}
