package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.javaclimb.common.Constants;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.Singer;
import com.javaclimb.mapper.SingerMapper;
import com.javaclimb.service.ISingerService;
import lombok.SneakyThrows;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-24
 */
@Service
public class SingerServiceImpl implements ISingerService {

    @Autowired
    private SingerMapper singerMapper;

    /**
     * 增加
     *
     * @param singer
     * @return
     */
    @Override
    public R insert(Singer singer) {
        if (singerMapper.insert(singer)>0){
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改
     *
     * @param singer
     */
    @Override
    public R update(Singer singer) {
        if (singerMapper.updateById(singer)>0){
            return R.success("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public R delete(Integer id) {
        if (singerMapper.deleteById(id)>0){
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
        return R.success(null,singerMapper.selectById(id));
    }

    /**
     * 查询所有歌手
     */
    @Override
    public R allSinger() {

        return R.success(null,singerMapper.selectList(null));
    }

    /**
     * 根据歌手名字模糊查询列表
     *
     * @param name
     */
    @Override
    public R singerOfName(String name) {
        LambdaQueryWrapper<Singer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(true,Singer::getName,name);
        return R.success(null,singerMapper.selectList(lambdaQueryWrapper));
    }

    /**
     * 根据歌手性别模糊查询列表
     *
     * @param sex
     */
    @Override
    public R singerOfSex(Integer sex) {
        LambdaQueryWrapper<Singer> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(true,Singer::getSex,sex);
        return R.success(null,singerMapper.selectList(lambdaQueryWrapper));
    }

    /**
     * 更新歌手头像
     *
     * @param picFile
     * @param id
     */
    @SneakyThrows
    @Override
    public R updateOfPic(MultipartFile picFile, int id) {
        if (picFile.isEmpty()){
            return R.error("文件上传失败");
        }
        String fileName = System.currentTimeMillis()+picFile.getOriginalFilename();
        String filePath = ResourceUtils.getURL("classpath:").getPath()+ "static/img/singerPic";
        File fPath = new File(filePath);
        if (!fPath.exists()){
            fPath.mkdir();
        }
        File file = new File(filePath+ Constants.PATHS + fileName);

        String storePic = "img/singerPic/"+fileName;
        try {
            picFile.transferTo(file);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storePic);
            boolean flag = singerMapper.updateById(singer)>0;
            if (flag){
                return R.success("上传成功",file);
            } else {
                return R.error("上传失败");
            }
        } catch (IOException e) {
            return R.error("上传失败",e.getMessage());
        } finally {
            return new R();
        }
    }
}
