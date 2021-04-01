package com.devsirlocust.casotech.entity;

import java.time.LocalDateTime;

public class Bill {

  private String client;
  private String address;
  private double totalAmount;
  private LocalDateTime date;

  public String getClient() {
    return this.client;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(final double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(final LocalDateTime date) {
    this.date = date;
  }

}
