package com.seckill2.dao;

import java.util.Date;

import com.seckill2.model.Seckill;

public interface SeckillMapper {

  int deleteByPrimaryKey(Integer seckillId);

  int insert(Seckill record);

  int insertSelective(Seckill record);

  Seckill selectByPrimaryKey(Integer seckillId);

  int updateByPrimaryKeySelective(Seckill record);

  int updateByPrimaryKey(Seckill record);

  int reduceNumber(Integer seckillId, Date killTime);

}