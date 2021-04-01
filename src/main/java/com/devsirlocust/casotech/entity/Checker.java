package com.devsirlocust.casotech.entity;

import org.springframework.stereotype.Service;

@Service
public class Checker {

  private final int IVA = 19;
  private final double costDelivery = 5000;

  public Bill finalBillCreate(final Bill bill) {
    double totalAmount = bill.getTotalAmount();
    if (totalAmount > 70_000 && totalAmount <= 100_000) {

      bill.setTotalAmount(this.generateIVA(totalAmount) + costDelivery);
    }
    if (totalAmount > 100_000) {
      bill.setTotalAmount(this.generateIVA(totalAmount));

    }

    return bill;
  }

  public double generateIVA(final Double amount) {
    return amount + ((amount * IVA) / 100);

  }
}
