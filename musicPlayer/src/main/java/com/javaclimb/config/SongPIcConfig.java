package com.javaclimb.config;

import com.javaclimb.common.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SongPIcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/img/songPic/**").addResourceLocations(
                "file:"+ Constants.SONGPIC_PATH
        );
    }
}
