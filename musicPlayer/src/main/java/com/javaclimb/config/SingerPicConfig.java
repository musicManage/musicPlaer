package com.javaclimb.config;


import com.javaclimb.common.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 歌手头像定位
 */
@Configuration
public class SingerPicConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "classpath:/static/img/singerPic/"
        );
    }

}
