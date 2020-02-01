package com.seckill2.exception;

public class SeckillException extends Exception {

  private static final long serialVersionUID = 1L;

  public SeckillException(String msg) {
    super(msg);
  }

  public SeckillException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
