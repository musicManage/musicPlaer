package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Collect;
import com.javaclimb.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收藏 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-12-06
 */
@RestController
@RequestMapping("/collect")
public class CollectController {


    @Autowired
    private ICollectService iCollectService;


    // 添加收藏的歌曲
    @PostMapping("/add")
    public R addCollection(Collect collect) {
        return iCollectService.addCollection(collect);
    }

    // 取消收藏的歌曲
    @PostMapping("/delete/{userId}/{songId}")
    public R deleteCollection(@PathVariable Integer userId, @PathVariable Integer songId) {
        return iCollectService.deleteCollect(userId, songId);
    }

    // 是否收藏歌曲
    @PostMapping("/status")
    public R isCollection(Collect collect) {
        return iCollectService.existSongId(collect);
    }

    // 返回的指定用户 ID 收藏的列表
    @GetMapping("/userId/{userId}")
    public R collectionOfUser(@PathVariable Integer userId) {
        return iCollectService.collectionOfUser(userId);
    }

    //通过歌名查找
    @GetMapping("/name/{userId}/{name}")
    public R selectOfName(@PathVariable String name,@PathVariable Integer userId){
        return iCollectService.selectByName(name,userId);
    }
}
