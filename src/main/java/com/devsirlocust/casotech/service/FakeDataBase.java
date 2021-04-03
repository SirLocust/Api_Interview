package com.devsirlocust.casotech.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.devsirlocust.casotech.entity.Bill;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class FakeDataBase {
  private List<Bill> dataBase = new ArrayList<>();

  public FakeDataBase() {
    this.dataBase = this.toolGenerateDefaultData();
  }

  public List<Bill> searchAll() {
    return this.dataBase;
  }

  public void save(final Bill bill) {
    this.dataBase.add(bill);
  }

  public Bill searchById(final String id) {
    for (Bill bill : this.dataBase) {
      if (bill.getId().equals(id)) {
        return bill;
      }
    }

    return null;
  }

  public Bill update(final Bill bill) {
    int whereIs = -2;
    for (int i = 0; i < this.dataBase.size(); i++) {
      if (this.dataBase.get(i).getId().equals(bill.getId())) {
        whereIs = i;
      }
    }
    if (whereIs < 0) {
      return null;
    }
    this.dataBase.set(whereIs, bill);
    return bill;
  }

  public boolean deleteBill(final String id) {
    // this.dataBase.removeIf(billtmp -> billtmp.getId().equals(bill.getId()));
    ListIterator<Bill> it = this.dataBase.listIterator();
    while (it.hasNext()) {
      if (it.next().getId().equals(id)) {
        it.remove();
        return true;
      }
    }

    return false;
  }

  private List<Bill> toolGenerateDefaultData() {
    List<Bill> defaulData = new ArrayList<>();

    Bill bill = new Bill();
    bill.setId("23456");
    bill.setClient("12345");
    bill.setAddress("Calle 1 # 3-3");
    bill.setAmount(87_555);
    bill.setPriceDelivery(5000);
    bill.generateIVA(19);
    bill.generateTotalAmount();
    bill.setDate(LocalDateTime.now());

    Bill bill2 = new Bill();
    bill2.setId("12345");
    bill2.setClient("23456");
    bill2.setAddress("Calle 2 # 6-6");
    bill2.setAmount(104_000);
    bill2.setPriceDelivery(0);
    bill2.generateIVA(19);
    bill2.generateTotalAmount();
    bill2.setDate(LocalDateTime.now());

    Bill bill3 = new Bill();
    bill3.setId("12346");
    bill3.setClient("23456");
    bill3.setAddress("Calle 2 # 6-6");
    bill3.setAmount(93_333);
    bill3.setPriceDelivery(5000);
    bill3.generateIVA(19);
    bill3.generateTotalAmount();
    bill3.setDate(LocalDateTime.now().minusHours(4).minusMinutes(20));

    Bill bill4 = new Bill();
    bill4.setId("34567");
    bill4.setClient("232345");
    bill4.setAddress("Calle 7 # 12-12");
    bill4.setAmount(105_333);
    bill4.setPriceDelivery(0);
    bill4.generateIVA(19);
    bill4.generateTotalAmount();
    bill4.setDate(LocalDateTime.now().minusHours(5).minusMinutes(20));

    Bill bill5 = new Bill();
    bill5.setId("45679");
    bill5.setClient("232345");
    bill5.setAddress("Calle 7 # 12-12");
    bill5.setAmount(105_333);
    bill5.setPriceDelivery(0);
    bill5.generateIVA(19);
    bill5.generateTotalAmount();
    bill5.setDate(LocalDateTime.now().minusHours(7).minusMinutes(20));

    Bill bill6 = new Bill();
    bill6.setId("45678");
    bill6.setClient("232345");
    bill6.setAddress("Calle 7 # 12-12");
    bill6.setAmount(105_333);
    bill6.setPriceDelivery(0);
    bill6.generateIVA(19);
    bill6.generateTotalAmount();
    bill6.setDate(LocalDateTime.now().minusHours(12).minusMinutes(20));

    defaulData.add(bill);
    defaulData.add(bill2);
    defaulData.add(bill3);
    defaulData.add(bill4);
    defaulData.add(bill5);
    defaulData.add(bill6);

    return defaulData;
  }
}
