package com.devsirlocust.casotech.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.devsirlocust.casotech.entity.Bill;
import com.devsirlocust.casotech.entity.Checker;
import com.devsirlocust.casotech.service.FakeDataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillsController {

  @Autowired
  private FakeDataBase fakeDataBase;

  @Autowired
  private Checker checker;

  @GetMapping("/bills")
  public List<Bill> searchAll() {
    return fakeDataBase.searchAll();
  }

  @PostMapping("/bills")
  public Bill save(@RequestBody Bill bill) {
    bill.setId(bill.generateId());
    bill.setDate(LocalDateTime.now());
    Bill newBill = checker.finalBillCreate(bill);
    fakeDataBase.save(newBill);
    return newBill;

  }

}
