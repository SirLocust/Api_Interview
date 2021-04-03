package com.devsirlocust.casotech.entity;

import java.time.LocalDateTime;

public class Bill {

  private String id;
  private String client;
  private String address;
  private double amount;
  private double priceDelivery;
  private double valueIVA;
  private double totalAmount;
  private LocalDateTime date;

  public void generateIVA(int iva) {
    this.setValueIVA((amount * iva) / 100);

  }

  public void generateTotalAmount() {
    this.setTotalAmount(this.amount + this.priceDelivery + this.valueIVA);
  }

  public String getClient() {
    return this.client;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public double getTotalAmount() {
    return this.totalAmount;
  }

  private void setTotalAmount(final double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public void setDate(final LocalDateTime date) {
    this.date = date;
  }

  public String generateId() {
    return String.valueOf(Math.round(Math.random() * 1000)) + this.getClient();
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public double getAmount() {
    return this.amount;
  }

  public double getPriceDelivery() {
    return this.priceDelivery;
  }

  public void setPriceDelivery(final double priceDelivery) {
    this.priceDelivery = priceDelivery;
  }

  public double getValueIVA() {
    return this.valueIVA;
  }

  public double generateCostCanselation() {
    return (this.amount * 10) / 100;
  }

  private void setValueIVA(final double iVA) {
    this.valueIVA = iVA;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

}
