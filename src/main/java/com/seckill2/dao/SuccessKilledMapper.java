package com.seckill2.dao;

import com.seckill2.model.SuccessKilled;
import com.seckill2.model.SuccessKilledKey;

public interface SuccessKilledMapper {

  int deleteByPrimaryKey(SuccessKilledKey key);

  int insert(SuccessKilled record);

  int insertSelective(SuccessKilled record);

  SuccessKilled selectByPrimaryKey(SuccessKilledKey key);

  int updateByPrimaryKeySelective(SuccessKilled record);

  int updateByPrimaryKey(SuccessKilled record);

}