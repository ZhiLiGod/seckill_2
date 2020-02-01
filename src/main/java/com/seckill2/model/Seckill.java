package com.seckill2.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Seckill implements Serializable {

  private static final long serialVersionUID = -1007261420092007193L;

  private Integer seckillId;

  private String name;

  private Integer number;

  private Date startTime;

  private Date endTime;

  private Date createTime;

}