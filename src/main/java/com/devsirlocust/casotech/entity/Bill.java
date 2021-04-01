package com.devsirlocust.casotech.entity;

import java.time.LocalDateTime;

public class Bill {

  private String id;
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

  public String generateId() {
    return String.valueOf(Math.round(Math.random() * 1000)) + this.getClient();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
