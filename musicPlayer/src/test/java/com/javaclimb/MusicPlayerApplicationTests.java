package com.javaclimb;

import com.javaclimb.common.Constants;
import com.javaclimb.controller.AdminController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicPlayerApplicationTests {

    @Autowired
    AdminController adminController = new AdminController();

    @Test
    void contextLoads() {
        System.out.println(Constants.PIC_PATH);
    }

}
