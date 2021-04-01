package com.devsirlocust.casotech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import com.devsirlocust.casotech.entity.Bill;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
// import org.hamcrest.*;

class CheckerTest {

  @Autowired
  private Checker checker;
  private Bill billOld;
  private Bill billNew;

  @Test
  void compare_two_hours() {
    billOld = new Bill();
    billNew = new Bill();
    checker = new Checker();
    billOld.setDate(LocalDateTime.now());
    billNew.setDate(billOld.getDate().plusHours(5));

    long time = checker.compareToHours(billOld, billNew);

    // assertThat(time, LessOrEqual(300));
    assertEquals(300, time);

  }
}
