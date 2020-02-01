package com.seckill2.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seckill2.model.Seckill;

public interface SeckillMapper {

  int deleteByPrimaryKey(Integer seckillId);

  int insert(Seckill record);

  int insertSelective(Seckill record);

  Seckill selectByPrimaryKey(Integer seckillId);

  int updateByPrimaryKeySelective(Seckill record);

  int updateByPrimaryKey(Seckill record);

  int reduceNumber(@Param("seckillId") Integer seckillId, @Param("killTime") Date killTime);

  List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}