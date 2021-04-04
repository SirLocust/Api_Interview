package com.devsirlocust.casotech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import com.devsirlocust.casotech.entity.Bill;

import org.hamcrest.Matchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

class CheckerTest {

  @Autowired
  private Checker checker;
  private Bill billOld;
  private Bill billNew;

  @BeforeEach
  void initTests() {
    this.checker = new Checker();
  }

  @Test
  @DisplayName("Should compare between two times and return diference")
  void should_Compare_Two_Hours() {
    billOld = new Bill();
    billNew = new Bill();
    billOld.setDate(LocalDateTime.now());
    billNew.setDate(billOld.getDate().plusHours(5));

    long time = checker.compareToHours(billOld.getDate(), billNew.getDate());

    MatcherAssert.assertThat((int) time, Matchers.lessThanOrEqualTo(300));
  }

  @Test
  @DisplayName("should return null when amount is less than 70_001")
  void should_return_null() {

    billOld = new Bill();
    billOld.setAmount(70_000);

    billNew = checker.finalBillCreate(billOld);

    assertNull(billNew);

  }

  @Test
  @DisplayName("should return bill with price delivery and iva when amount is granted than 70_001 and less 100001 ")
  void should_return_bill_with_priceDelivery_and_iva() {

    billOld = new Bill();
    billOld.setAmount(75_000);

    billNew = checker.finalBillCreate(billOld);

    assertEquals(5000, billNew.getPriceDelivery());
    assertEquals(14250, billNew.getValueIVA());
    assertEquals(94250, billNew.getTotalAmount());

  }

  @Test
  @DisplayName("should return bill with price delivery same 0 and iva when amount is granted than 100000")
  void should_return_bill_with_priceDelivery_same_0_and_iva() {

    billOld = new Bill();
    billOld.setAmount(104_000);

    billNew = checker.finalBillCreate(billOld);

    assertEquals(0, billNew.getPriceDelivery());
    assertEquals(19760, billNew.getValueIVA());
    assertEquals(123760, billNew.getTotalAmount());

  }

}
