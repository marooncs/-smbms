package com.shi.service.bill;

import com.shi.pojo.Bill;

import java.util.List;
import java.util.Map;

public interface billService {
    int getBillCount(String productName, int providerId, int isPayment);
    List<Bill> getBillList(String productName, int providerId, int isPayment, int currentPageNo, int pageSize);
    Bill getBillById(int id);
    boolean addBill(Bill bill);
    boolean modifyBill(Bill bill);
    boolean delBill(int id);
}
