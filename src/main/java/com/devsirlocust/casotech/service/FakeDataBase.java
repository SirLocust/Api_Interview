package com.devsirlocust.casotech.service;

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
}
