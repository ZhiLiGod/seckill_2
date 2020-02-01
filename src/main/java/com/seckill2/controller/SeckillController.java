package com.seckill2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seckill2.model.Seckill;
import com.seckill2.service.SeckillService;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

  @Autowired
  private SeckillService seckillService;

  @GetMapping("/test")
  public Seckill get() {
    return seckillService.test();
  }

}
