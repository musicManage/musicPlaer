package com.javaclimb;

import com.javaclimb.common.Constants;
import com.javaclimb.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@SpringBootTest
class MusicPlayerApplicationTests {

    @Autowired
    AdminController adminController = new AdminController();

    @Test
    void contextLoads() throws FileNotFoundException {
        System.out.println("file:"+Constants.PROJECT_PATH+Constants.PATHS+"img"+Constants.PATHS+"singerPic"+Constants.PATHS);
    }

}
