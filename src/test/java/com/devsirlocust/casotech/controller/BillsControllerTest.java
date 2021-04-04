package com.devsirlocust.casotech.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.devsirlocust.casotech.entity.Bill;
import com.devsirlocust.casotech.service.Checker;
import com.devsirlocust.casotech.service.FakeDataBase;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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

  @WithMockUser("admin")
  @Test
  @DisplayName("Should List All Bills when making GET request to endpoit - /api/bills")
  public void shouldListAllBills() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/api/bills")).andExpect(MockMvcResultMatchers.status().is(200));
  }

  @WithMockUser("admin")
  @Test
  @DisplayName("Should create new bill with price is range 70001-100000  when making POST request to endpoit - /api/bills")
  public void shouldCreateNewBillPrice75_000() throws Exception {

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
        .andExpect(MockMvcResultMatchers.jsonPath("$.priceDelivery").value(5000))
        .andExpect(MockMvcResultMatchers.jsonPath("$.valueIVA").value(14250));
  }

  @WithMockUser("admin")
  @Test
  @DisplayName("Should create new bill with price is range 70001-100000  when making POST request to endpoit - /api/bills")
  public void shouldCreateNewBillPrice100_000() throws Exception {

    Bill bill = new Bill();

    bill.setAddress("calle 435");
    bill.setClient("123423");
    bill.setAmount(104000);
    bill.setPriceDelivery(0);
    bill.generateIVA(19);
    bill.generateTotalAmount();

    when(checker.finalBillCreate(any(Bill.class))).thenReturn(bill);
    String jsonBill = new ObjectMapper().writeValueAsString(bill);
    mockMvc
        .perform(MockMvcRequestBuilders.post("/api/bills").contentType(MediaType.APPLICATION_JSON).content(jsonBill)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(8)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.priceDelivery").value(0))
        .andExpect(MockMvcResultMatchers.jsonPath("$.valueIVA").value(19760));

    ;

  }

  @WithMockUser("admin")
  @Test
  @DisplayName("Should modific existing bill when time is more that required  when making PUT request to endpoit - /api/bills")
  public void should_Update_Bill_when_time_is_more() throws Exception {

    Bill bill = new Bill();
    bill.setId("12323");
    bill.setAddress("calle 435");
    bill.setClient("123423");
    bill.setAmount(99.999);

    when(fakeDataBase.searchById(any(String.class))).thenReturn(bill);

    when(checker.finalBillUpdate(any(Bill.class), any(Bill.class))).thenReturn(null);
    String jsonBill = new ObjectMapper().writeValueAsString(bill);
    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/bills").contentType(MediaType.APPLICATION_JSON).content(jsonBill)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1))).andExpect(
            MockMvcResultMatchers.jsonPath("$.Message").value("change impossible, the time of change was exhausted"));

    ;

  }

  @WithMockUser("admin")
  @Test
  @DisplayName("Should modific existing bill with less price total when making PUT request to endpoit - /api/bills")
  public void should_Update_Bill_Price_less_total() throws Exception {

    Bill bill = new Bill();
    bill.setId("12323");
    bill.setAddress("calle 435");
    bill.setClient("123423");
    bill.setAmount(99.999);

    when(fakeDataBase.searchById(any(String.class))).thenReturn(bill);

    when(checker.finalBillUpdate(any(Bill.class), any(Bill.class))).thenReturn(bill);
    String jsonBill = new ObjectMapper().writeValueAsString(bill);
    mockMvc
        .perform(MockMvcRequestBuilders.put("/api/bills").contentType(MediaType.APPLICATION_JSON).content(jsonBill)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.Message").value("the amount is low "));

    ;

  }

}
