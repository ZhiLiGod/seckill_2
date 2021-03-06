package com.seckill2.service;

import java.util.List;

import com.seckill2.dto.ExposerDto;
import com.seckill2.dto.SeckillDto;
import com.seckill2.dto.SeckillExecution;
import com.seckill2.exception.RepeatKillException;
import com.seckill2.exception.SeckillCloseException;
import com.seckill2.exception.SeckillException;

public interface SeckillService {

  List<SeckillDto> getSeckills();

  SeckillDto getById(Integer seckillId);

  ExposerDto exportSeckillUrl(Integer seckillId);

  SeckillExecution executeSeckill(Integer seckillId, Long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;

  SeckillExecution executeSeckillByProcedure(Integer seckillId, Long userPhone, String md5) throws SeckillException;

}
