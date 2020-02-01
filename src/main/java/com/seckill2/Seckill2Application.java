package com.seckill2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.seckill2.dao")
public class Seckill2Application {

  public static void main(String[] args) {
    SpringApplication.run(Seckill2Application.class, args);
  }

}
