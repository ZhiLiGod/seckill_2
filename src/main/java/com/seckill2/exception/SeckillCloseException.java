package com.seckill2.exception;

public class SeckillCloseException extends Exception {

  private static final long serialVersionUID = 8144280845201983485L;

  public SeckillCloseException(String msg) {
    super(msg);
  }

  public SeckillCloseException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
