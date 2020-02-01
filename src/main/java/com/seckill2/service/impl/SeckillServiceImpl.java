package com.seckill2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seckill2.dao.SeckillMapper;
import com.seckill2.model.Seckill;
import com.seckill2.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

  @Autowired
  private SeckillMapper seckillMapper;

  @Override
  public Seckill test() {
    return seckillMapper.selectByPrimaryKey(1);
  }

}
