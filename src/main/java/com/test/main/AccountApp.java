package com.test.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.test")
public class AccountApp {
    public static void main(String...args){

        SpringApplication.run(AccountApp.class,args);

    }
}
