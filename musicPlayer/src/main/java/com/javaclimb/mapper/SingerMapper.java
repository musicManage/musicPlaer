package mapper;

import entity.Singer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 歌手 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-11-24
 */
@Mapper
public interface SingerMapper extends BaseMapper<Singer> {

}
