package com.seckill2.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExposerDto {

  private boolean exposed;
  private String md5;
  private Integer seckillId;
  private LocalDateTime now;
  private LocalDateTime start;
  private LocalDateTime end;

}
