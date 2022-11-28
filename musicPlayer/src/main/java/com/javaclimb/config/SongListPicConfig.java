package com.javaclimb.config;

import com.javaclimb.common.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SongListPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/img/songListPic/**").addResourceLocations(
                "file:"+ Constants.SONGLISTPIC_PATH
        );
    }
}
