package com.seckill2.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SeckillDto {

  private Integer id;
  private String name;
  private LocalDateTime createTime;
  private LocalDateTime startTime;
  private LocalDateTime endTime;

}
