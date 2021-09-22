package com.shi.dao.bill;

import com.shi.pojo.Bill;

import java.util.List;
import java.util.Map;

public interface billDao {
    int getBillCount(Map map);

    List<Bill> getBillList(Map map);

    Bill getBillById(int id);

    int addBill(Bill bill);

    int modifyBill(Bill bill);

    int delBill(int id);
}
