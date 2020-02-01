package com.seckill2.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seckill2.dto.ExposerDto;
import com.seckill2.dto.SeckillDto;
import com.seckill2.dto.SeckillExecution;
import com.seckill2.exception.RepeatKillException;
import com.seckill2.exception.SeckillCloseException;
import com.seckill2.exception.SeckillException;
import com.seckill2.service.SeckillService;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

  @Autowired
  private SeckillService seckillService;

  @GetMapping("/list")
  public List<SeckillDto> getAll() {
    return seckillService.getSeckills();
  }

  @GetMapping("/{id}/detail")
  public SeckillDto getById(@PathVariable("id") final Integer seckillId) {
    return seckillService.getById(seckillId);
  }

  @GetMapping("/{id}/exposer")
  public ExposerDto exposer(@PathVariable("id") final Integer seckillId) {
    return seckillService.exportSeckillUrl(seckillId);
  }

  @PostMapping("/{id}/{md5}/{phone}/execute")
  @ResponseStatus(value = HttpStatus.OK)
  public SeckillExecution execute(@PathVariable("id") final Integer seckillId, @PathVariable final String md5, @PathVariable final Long phone)
      throws SeckillException, RepeatKillException, SeckillCloseException {
    return seckillService.executeSeckill(seckillId, phone, md5);
  }

  @GetMapping("/time/now")
  public LocalDateTime time() {
    return LocalDateTime.now();
  }

}
