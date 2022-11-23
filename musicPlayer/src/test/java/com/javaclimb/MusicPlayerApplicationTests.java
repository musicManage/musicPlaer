package com.javaclimb;

import com.javaclimb.controller.AdminController;
import com.javaclimb.service.AdminService;
import com.javaclimb.service.impl.AdminServiceImpl;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;

@SpringBootTest
class MusicPlayerApplicationTests {

    @Autowired
    AdminController adminController = new AdminController();

    @Test
    void contextLoads() {
    }

}
