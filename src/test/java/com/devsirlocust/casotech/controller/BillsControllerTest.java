package com.devsirlocust.casotech.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import com.devsirlocust.casotech.entity.Bill;
import com.devsirlocust.casotech.service.Checker;
import com.devsirlocust.casotech.service.FakeDataBase;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = BillsController.class)

public class BillsControllerTest {

  @MockBean
  private Checker checker;
  @MockBean
  private FakeDataBase fakeDataBase;
  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Should List All Bills when making GET request to endpoit - /api/bills")
  public void shouldListAllBills() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/bills")).andExpect(MockMvcResultMatchers.status().is(200));
  }

  @Test
  @DisplayName("Should create new bill with price  when making POST request to endpoit - /api/bills")
  public void shouldCreateNewBill() throws Exception {

    Bill bill = new Bill();

    bill.setAddress("calle 435");
    bill.setClient("123423");
    bill.setAmount(75000);
    bill.setPriceDelivery(5000);
    bill.generateIVA(19);
    bill.generateTotalAmount();

    when(checker.finalBillCreate(any(Bill.class))).thenReturn(bill);
    String jsonBill = new ObjectMapper().writeValueAsString(bill);
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/bills").contentType(MediaType.APPLICATION_JSON).content(jsonBill)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(8)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.priceDelivery").value(5000));
    ;

  }

}
