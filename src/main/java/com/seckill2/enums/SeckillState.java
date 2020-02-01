package com.seckill2.enums;

public enum SeckillState {

  FAILED(0, "Failed"), SUCCESS(1, "Success");

  private Integer state;
  private String text;

  public Integer getState() {
    return state;
  }

  public String getText() {
    return text;
  }

  private SeckillState(Integer state, String text) {
    this.state = state;
    this.text = text;
  }

}
