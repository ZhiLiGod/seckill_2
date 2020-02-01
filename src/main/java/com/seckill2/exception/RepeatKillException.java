package com.seckill2.exception;

public class RepeatKillException extends Exception {

  private static final long serialVersionUID = 1L;

  public RepeatKillException(String msg) {
    super(msg);
  }

  public RepeatKillException(String msg, Throwable cause) {
    super(msg, cause);
  }

}
