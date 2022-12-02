package com.javaclimb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.javaclimb.common.Constants;
import com.javaclimb.controller.util.R;
import com.javaclimb.entity.ListSong;
import com.javaclimb.entity.Singer;
import com.javaclimb.entity.Song;
import com.javaclimb.entity.SongList;
import com.javaclimb.mapper.SongMapper;
import com.javaclimb.service.ISongService;
import com.javaclimb.vo.SongAndSingerVo;
import com.javaclimb.vo.SongInListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 歌曲 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-27
 */
@Service
public class SongServiceImpl implements ISongService {

    @Autowired
    private SongMapper songMapper;

    /**
     * 增加
     *
     * @param song
     * @return
     */

    @Override
    public R insert(Song song, MultipartFile mpfile) {
        String pic = "img/songPic/tubiao.jpg";
        String fileName = mpfile.getOriginalFilename();
        String filePath = Constants.SONG_PATH;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return R.error("创建文件失败");
            }
        }
        File dest = new File(filePath + Constants.PATHS + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setUrl(storeUrlPath);
            if (songMapper.insert(song)>0){
                return R.success("添加成功");
            } else {
                return R.error("添加失败");
            }
        } catch (IOException e) {
            return R.error("上传失败" + e.getMessage());
        }
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public R update(Song song) {
        if (songMapper.updateById(song)>0){
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
        Song song = songMapper.selectById(id);
        deleteOfFile(song.getUrl());
        deleteOfPic(song.getPic());
        if (songMapper.deleteById(id)>0){
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
        return R.success(null,songMapper.selectById(id));
    }

    /**
     * 查询所有歌手
     */
    @Override
    public R allSong() {
        return R.success(null,songMapper.selectList(null));
    }

    /**
     * 根据歌名模糊查询列表
     *
     * @param name
     */
    @Override
    public R songOfName(String name) {
        LambdaQueryWrapper<Song> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(true,Song::getName,name);
        return R.success(null,songMapper.selectList(lambdaQueryWrapper));
    }

    /**
     * 根据歌名精确查询
     *
     * @param name
     */
    @Override
    public R selectSongOfName(String name) {
        List<SongAndSingerVo> songInListVos = songMapper.selectJoinList(SongAndSingerVo.class,
                new MPJLambdaWrapper<Song>()
                        .selectAll(Song.class)
                        .selectAs(Singer::getName,SongAndSingerVo::getSingerName)
                        .innerJoin(Singer.class, Singer::getId, Song::getSingerId)
                        .eq(Singer::getName,name)
        );
        return  R.success(null,songInListVos);
    }

    /**
     * 根据歌手ID模糊查询歌曲
     *
     * @param singerID
     */
    @Override
    public R songOfSingerID(Integer singerID) {
        LambdaQueryWrapper<Song> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(true,Song::getSingerId,singerID);
        return R.success(null,songMapper.selectList(lambdaQueryWrapper));
    }

    @Override
    public R updateOfPic(MultipartFile picFile, int id) {
        if (picFile.isEmpty()){
            return R.error("文件上传失败");
        }
        String fileName = System.currentTimeMillis()+picFile.getOriginalFilename();
        String filePath = Constants.SONGPIC_PATH;
        File fPath = new File(filePath);
        if (!fPath.exists()){
            fPath.mkdir();
        }
        File file = new File(filePath+ Constants.PATHS + fileName);

        String storePic = "img/songPic/"+fileName;
        try {
            picFile.transferTo(file);
            Song song = songMapper.selectById(id);
            deleteOfPic(song.getPic());
            song.setPic(storePic);
            boolean flag = songMapper.updateById(song)>0;
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
     * 删除原有歌曲头像
     *
     * @param path
     */
    @Override
    public R deleteOfPic(String path) {
        if (path.equals("img/songPic/tubiao.jpg")){
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
     * 更新歌曲文件
     *
     * @param urlFile
     * @param id
     */
    @Override
    public R updateOfFile(MultipartFile urlFile, int id) {
        String fileName = System.currentTimeMillis()+urlFile.getOriginalFilename();
        String filePath = Constants.SONG_PATH;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            if (!file1.mkdir()) {
                return R.error("创建目的文件夹失败");
            }
        }
        File dest = new File(filePath + Constants.PATHS + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = songMapper.selectById(id);
            deleteOfFile(song.getUrl());
            song.setUrl(storeUrlPath);
            if (songMapper.updateById(song) > 0) {
                return R.success("更新成功", storeUrlPath);
            } else {
                return R.error("更新失败");
            }
        } catch (IOException e) {
            return R.error("更新失败" + e.getMessage());
        }
    }

    /**
     * 删除原有歌曲文件
     *
     * @param path
     */
    @Override
    public R deleteOfFile(String path) {
        String fileOldPath = Constants.RESOURCE+path;
        boolean b = FileSystemUtils.deleteRecursively(new File(fileOldPath));
        if (b){
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
