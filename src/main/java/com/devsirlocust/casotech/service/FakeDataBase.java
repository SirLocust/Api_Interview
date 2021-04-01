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
}
