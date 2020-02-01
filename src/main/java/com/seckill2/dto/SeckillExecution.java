package com.seckill2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeckillExecution {

  private Integer seckillId;
  private int state;
  private String stateInfo;
  private SuccessKilledDto successKilledDto;

}
