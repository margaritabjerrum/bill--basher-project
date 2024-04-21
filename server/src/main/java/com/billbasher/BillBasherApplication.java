package com.billbasher;

import com.billbasher.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//TEST FILE TO CHECK USERS
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class BillBasherApplication{

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BillBasherApplication.class, args);
    }
}
