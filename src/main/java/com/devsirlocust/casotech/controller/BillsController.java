package com.devsirlocust.casotech.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.devsirlocust.casotech.entity.Bill;
import com.devsirlocust.casotech.service.Checker;
import com.devsirlocust.casotech.service.FakeDataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<?> save(@RequestBody Bill bill) {
    bill.setId(bill.generateId());
    bill.setDate(LocalDateTime.now());
    Bill newBill = checker.finalBillCreate(bill);
    if (newBill == null) {
      return new ResponseEntity<>("No cumples con los requisitos", HttpStatus.BAD_REQUEST);
    }
    fakeDataBase.save(newBill);
    return new ResponseEntity<>(newBill, HttpStatus.OK);

  }

  @PutMapping("/bills")
  public ResponseEntity<?> update(@RequestBody Bill newBill) {
    Bill oldBill = fakeDataBase.searchById(newBill.getId());
    if (oldBill == null) {
      return new ResponseEntity<>("Bill not found,verify your id bill", HttpStatus.NOT_FOUND);
    }
    Bill billCheck = checker.finalBillUpdate(oldBill, newBill);

    if (billCheck == null) {
      return new ResponseEntity<>("change impossible, the time of change was exhausted", HttpStatus.REQUEST_TIMEOUT);
    }
    Bill billUpdated = fakeDataBase.update(newBill);
    if (billUpdated == null) {
      return new ResponseEntity<>("error , item not has saved", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(billUpdated, HttpStatus.OK);

  }

}
