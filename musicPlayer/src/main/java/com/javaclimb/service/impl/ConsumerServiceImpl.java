package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaclimb.common.Constants;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Consumer;
import com.javaclimb.mapper.ConsumerMapper;
import com.javaclimb.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <p>
 * 前端用户 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-28
 */
@Service
public class ConsumerServiceImpl implements IConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 增加
     *
     * @param consumer
     * @return
     */
    @Override
    public R insert(Consumer consumer) {
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<Consumer>();
        wrapper.eq(Consumer::getUsername,consumer.getUsername());
        if (!consumerMapper.selectList(wrapper).isEmpty()){
            return R.error("添加失败");
        }
        consumer.setAvator("/img/userPic/user.jpg");
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        if (consumerMapper.insert(consumer)>0){
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改
     *
     * @param consumer
     */
    @Override
    public R update(Consumer consumer) {
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<Consumer>();
        wrapper.eq(Consumer::getUsername,consumer.getUsername());
        if (!(consumerMapper.selectList(wrapper).isEmpty())
                && !(consumerMapper.selectById(consumer.getId()).getUsername().equals(consumer.getUsername()))){
            return R.error("更新失败");
        }
        if (consumerMapper.updateById(consumer)>0){
            return R.success("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    /**
     * 修改密码
     *
     * @param consumer
     */
    @Override
    public R updatePass(Consumer consumer) {
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Consumer::getUsername,consumer.getUsername());
        Consumer consumer1 = consumerMapper.selectOne(wrapper);
        consumer1.setPassword(consumer.getPassword());
        if (consumerMapper.updateById(consumer1)>0){
            return R.success("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public R delete(Integer id) {
        if (consumerMapper.deleteById(id)>0){
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 根据主键查询整个对象
     *
     * @param id
     */
    @Override
    public R selectByPrimaryKey(Integer id) {
        return R.success(null,consumerMapper.selectById(id));
    }

    /**
     * 查询所有用户
     */
    @Override
    public R allConsumer() {
        return R.success(null,consumerMapper.selectList(null));
    }

    /**
     * 根据用户名字模糊查询列表
     *
     * @param name
     */
    @Override
    public R consumerOfName(String name) {
        LambdaQueryWrapper<Consumer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(true,Consumer::getUsername,name);
        return R.success(null,consumerMapper.selectList(lambdaQueryWrapper));
    }

    /**
     * 检测用户名是否重复
     *
     * @param name
     */
    @Override
    public R verifyOfName(String name) {
        LambdaQueryWrapper<Consumer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Consumer::getUsername,name);
        if (consumerMapper.selectList(lambdaQueryWrapper).isEmpty()){
            return R.success("用户名可用");
        } else {
            return R.error("用户名不可用");
        }
    }

    /**
     * 根据用户性别模糊查询列表
     *
     * @param sex
     */
    @Override
    public R consumerOfSex(Integer sex) {
        LambdaQueryWrapper<Consumer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(true,Consumer::getSex,sex);
        return R.success(null,consumerMapper.selectList(lambdaQueryWrapper));
    }

    /**
     * 更新用户头像
     *
     * @param picFile
     * @param id
     */
    @Override
    public R updateOfPic(MultipartFile picFile, int id) {
        if (picFile.isEmpty()){
            return R.error("文件上传失败");
        }
        String fileName = System.currentTimeMillis()+picFile.getOriginalFilename();
        String filePath = Constants.USERPIC_PATH;
        File fPath = new File(filePath);
        if (!fPath.exists()){
            fPath.mkdir();
        }
        File file = new File(filePath+ Constants.PATHS + fileName);

        String storePic = "img/userPic/"+fileName;
        try {
            picFile.transferTo(file);
            Consumer consumer = consumerMapper.selectById(id);
            deleteOfPic(consumer.getAvator());
            consumer.setAvator(storePic);
            boolean flag = consumerMapper.updateById(consumer)>0;
            if (flag){
                return R.success("上传成功",file);
            } else {
                return R.error("上传失败");
            }
        } catch (IOException e) {
            return R.error("上传失败",e.getMessage());
        }
    }

    /**
     * 删除原有用户头像
     *
     * @param path
     */
    @Override
    public R deleteOfPic(String path) {
        if (path.equals("/img/userPic/user.jpg")){
            return R.error("不能删除默认头像图片");
        }
        String picOldPath = Constants.RESOURCE+path;
        boolean b = FileSystemUtils.deleteRecursively(new File(picOldPath));
        if (b){
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 校验密码
     *
     * @param consumer
     */
    @Override
    public R verifyPass(Consumer consumer) {
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<Consumer>();
        wrapper.eq(Consumer::getUsername,consumer.getUsername());
        wrapper.eq(Consumer::getPassword,consumer.getPassword());
        if (!(consumerMapper.selectList(wrapper).isEmpty())){
            return R.success("校验成功",consumerMapper.selectOne(wrapper));
        } else {
            return R.error("校验失败");
        }
    }

    /**
     * 查询用户邮箱
     *
     * @param username
     */
    @Override
    public R getEmail(String username) {
        LambdaQueryWrapper<Consumer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Consumer::getUsername,username);
        if (!(consumerMapper.selectList(wrapper).isEmpty())){
            return R.success("查找成功",consumerMapper.selectOne(wrapper).getEmail());
        } else {
            return R.error("查找失败");
        }
    }
}
