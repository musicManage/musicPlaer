package com.javaclimb.controller;

import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Consumer;
import com.javaclimb.entity.Singer;
import com.javaclimb.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端用户 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private IConsumerService iConsumerService;

    /**
     * 添加用户
     * @param consumer
     * @return
     */
    @PostMapping("/add")
    public R addConsumer(Consumer consumer){
        return iConsumerService.insert(consumer);
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    public R delConsumer(Integer id){
        return iConsumerService.delete(id);
    }

    /**
     * 返回全部用户
     */
    @GetMapping("/")
    public R allConsumer(){
        return iConsumerService.allConsumer();
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/update")
    public R updateConsumer(Consumer consumer){
        return iConsumerService.update(consumer);
    }

    /**
     * 修改用户密码
     */
    @PostMapping("/pass/update")
    public R updateConsumerPass(Consumer consumer){
        return iConsumerService.updatePass(consumer);
    }

    /**
     * 返回含指定用户名的用户
     */
    @GetMapping("/name/{name}")
    public R consumerOfName(@PathVariable(value = "name") String name){
        return iConsumerService.consumerOfName(name);
    }

    /**
     * 精确查找用户
     */
    @GetMapping("/verify/{name}")
    public R consumerOfFName(@PathVariable(value = "name") String name){
        return iConsumerService.verifyOfName(name);
    }

    /**
     * 更新用户头像
     */
    @PostMapping("/pic/update/{id}")
    public R updateSinger(@RequestParam("file") MultipartFile picFile, @PathVariable(value = "id") int id){
        return iConsumerService.updateOfPic(picFile,id);
    }

    /**
     * 校验密码
     */
    @PostMapping("/verifyPass")
    public R verifyUser(Consumer consumer){
        return iConsumerService.verifyPass(consumer);
    }

    /**
     * 获得用户邮箱
     */
    @GetMapping("/email/{name}")
    public R getEmailOfName(@PathVariable(value = "name") String name){
        return iConsumerService.getEmail(name);
    }
}
