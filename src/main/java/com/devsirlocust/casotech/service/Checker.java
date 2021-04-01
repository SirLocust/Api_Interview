package com.devsirlocust.casotech.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import com.devsirlocust.casotech.entity.Bill;

import org.springframework.stereotype.Service;

@Service
public class Checker {

  private final int IVA = 19;
  private final double CONST_DELIVERY = 5000;

  public Bill finalBillCreate(final Bill bill) {
    double amount = bill.getAmount();
    if (amount <= 70_000) {
      return null;
    }
    if (amount > 70_000 && amount <= 100_000) {
      bill.setPriceDelivery(this.CONST_DELIVERY);

    }
    bill.generateIVA(this.IVA);
    bill.generateTotalAmount();

    return bill;
  }

  public Bill finalBillUpdate(Bill oldBill, Bill newBill) {
    if (compareToHours(oldBill.getDate(), newBill.getDate()) > 300) {
      return null;
    }
    if (oldBill.getAmount() > newBill.getAmount()) {

      return null;
    }
    if (newBill.getAmount() > 100_000) {
      newBill.setPriceDelivery(0);
    }
    newBill.generateIVA(this.IVA);
    newBill.generateTotalAmount();
    return newBill;
  }

  public long compareToHours(final LocalDateTime oldTime, final LocalDateTime newTime) {

    return ChronoUnit.MINUTES.between(oldTime, newTime);
  }

  public double finalBillDelete(Bill bill) {
    if (compareToHours(bill.getDate(), LocalDateTime.now()) > 720) {
      return bill.generateCostCanselation();
    }
    return 0;
  }
}
