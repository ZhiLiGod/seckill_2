package com.seckill2.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.seckill2.dao.SeckillMapper;
import com.seckill2.model.Seckill;
import com.seckill2.util.RedisUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisDao {

  private static final String SECKILL_PREFIX = "seckillId";
  private static final String SECKILL_LIST_PREFIX = "seckills";

  @Autowired
  private RedisUtil redisUtil;

  @Autowired
  private SeckillMapper seckillMapper;

  public Seckill getSeckill(Integer seckillId) {
    Object obj = redisUtil.get(SECKILL_PREFIX + seckillId);
    log.info("" + obj);
    Seckill seckill = (Seckill) obj;

    if (seckill == null) {
      seckill = seckillMapper.selectByPrimaryKey(seckillId);
      redisUtil.set(SECKILL_PREFIX + seckillId, seckill);
      log.info("Read from DB");
    }

    return seckill;
  }

  public List<Seckill> getSeckillList() {
    List<Seckill> seckillList = (List<Seckill>) redisUtil.get(SECKILL_LIST_PREFIX);

    if (CollectionUtils.isEmpty(seckillList)) {
      seckillList = seckillMapper.queryAll(0, Integer.MAX_VALUE);
      redisUtil.set(SECKILL_LIST_PREFIX, seckillList);
      log.info("Read From DB");
    }

    return seckillList;
  }

}
