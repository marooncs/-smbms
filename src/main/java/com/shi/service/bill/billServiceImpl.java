package com.shi.service.bill;

import com.shi.dao.bill.billDao;
import com.shi.dao.bill.billDaoImpl;
import com.shi.pojo.Bill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class billServiceImpl implements billService{
    private billDao billdao;
    public billServiceImpl(){
        billdao = new billDaoImpl();
    }

    public int getBillCount(String productName, int providerId, int isPayment) {
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productName);
        map.put("providerId", providerId);
        map.put("isPayment", isPayment);
        return billdao.getBillCount(map);
    }

    public List<Bill> getBillList(String productName, int providerId, int isPayment, int currentPageNo, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productName);
        map.put("providerId", providerId);
        map.put("isPayment", isPayment);
        map.put("currentPageNo", (currentPageNo-1)*pageSize);
        map.put("pageSize", pageSize);
        return billdao.getBillList(map);
    }

    public Bill getBillById(int id) {
        return billdao.getBillById(id);
    }

    public boolean addBill(Bill bill) {
        int rows = billdao.addBill(bill);
        boolean flag = false;
        if(rows > 0){
            flag = true;
        }
        return flag;
    }

    public boolean modifyBill(Bill bill) {
        int rows = billdao.modifyBill(bill);
        boolean flag = false;
        if(rows > 0){
            flag = true;
        }
        return flag;
    }

    public boolean delBill(int id) {
        int rows = billdao.delBill(id);
        boolean flag = false;
        if(rows > 0){
            flag = true;
        }
        return flag;
    }
}
