package com.devsirlocust.casotech.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devsirlocust.casotech.entity.Bill;
import com.devsirlocust.casotech.service.Checker;
import com.devsirlocust.casotech.service.FakeDataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    Map<String, Object> response = new HashMap<>();
    bill.setId(bill.generateId());
    bill.setDate(LocalDateTime.now());
    Bill newBill = checker.finalBillCreate(bill);
    if (newBill == null) {
      response.put("Message", "the total value of your order must be greater than 70,000 ");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    fakeDataBase.save(newBill);
    return new ResponseEntity<>(newBill, HttpStatus.OK);

  }

  @PutMapping("/bills")
  public ResponseEntity<?> update(@RequestBody Bill newBill) {
    Map<String, Object> response = new HashMap<>();
    Bill oldBill = fakeDataBase.searchById(newBill.getId());

    if (oldBill == null) {
      response.put("Message", "Bill not found,verify your id bill");
      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    Bill billCheck = checker.finalBillUpdate(oldBill, newBill);

    if (billCheck == null) {
      response.put("Message", "change impossible, the time of change was exhausted");
      return new ResponseEntity<>(response, HttpStatus.REQUEST_TIMEOUT);
    }
    Bill billUpdated = fakeDataBase.update(newBill);
    if (billUpdated == null) {
      response.put("Message", "error , item not has saved");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>(billUpdated, HttpStatus.OK);

  }

  @DeleteMapping("/bills/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") String idBill) {
    Map<String, Object> response = new HashMap<>();
    Bill searchBill = fakeDataBase.searchById(idBill);
    if (searchBill == null) {
      response.put("Message", "error , bill not found");

      return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    double costDelete = checker.finalBillDelete(searchBill);

    boolean wasDelete = fakeDataBase.deleteBill(idBill);

    if (!wasDelete) {
      response.put("Message", "error , not removed bill");

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    if (costDelete > 0) {
      response.put("Message", "you have one cost for cancel  : " + costDelete);

      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    response.put("Message", "bill delete Correct");

    return new ResponseEntity<>(response, HttpStatus.OK);

  }
}
