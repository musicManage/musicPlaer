package com.javaclimb.controller.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * mybatis-plus 代码生成器
 */
public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/music?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("admin") // 设置作者
                            .outputDir("D:\\SpringBoot\\music\\musicPlayer\\src\\main\\java\\com\\javaclimb"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\SpringBoot\\music\\musicPlayer\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("singer") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .strategyConfig(builder -> {

                    //实体类相关配置

                    builder.entityBuilder()

                            .enableTableFieldAnnotation() // 开启实体类字段注解

                            .enableLombok() // 开启lombok

                            .enableChainModel() // 开启链式模式

                            .enableFileOverride(); // 覆盖已生成文件

                })
                .execute();

    }
}
