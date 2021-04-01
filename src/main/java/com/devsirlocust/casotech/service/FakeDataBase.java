package com.devsirlocust.casotech.service;

import java.util.ArrayList;
import java.util.List;

import com.devsirlocust.casotech.entity.Bill;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class FakeDataBase {
  private List<Bill> dataBase = new ArrayList<>();

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
}
