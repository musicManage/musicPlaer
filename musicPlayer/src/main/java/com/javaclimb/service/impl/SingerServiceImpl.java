package service.impl;

import entity.Singer;
import mapper.SingerMapper;
import service.ISingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 歌手 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-11-24
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements ISingerService {

}
