package com.shi.servlet.bill;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.shi.Utiles.Constant;
import com.shi.Utiles.PageSupport;
import com.shi.pojo.Bill;
import com.shi.pojo.Provider;
import com.shi.pojo.User;
import com.shi.service.bill.billServiceImpl;
import com.shi.service.provider.providerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null && method.equals("query")){
            this.query(req, resp);
        }
        if(method != null && method.equals("viewBill")){
            this.getBillById(req, resp, "billview.jsp");
        }
        if(method != null && method.equals("modifyBill")){
            this.getBillById(req, resp, "billmodify.jsp");
        }
        if(method != null && method.equals("addBill")){
            this.addBill(req, resp);
        }
        if(method != null && method.equals("modify")){
            this.modifyBill(req, resp);
        }
        if(method != null && method.equals("delBill")){
            this.delBill(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String queryProductName = req.getParameter("queryProductName");
        String temppid = req.getParameter("queryProviderId");
        String tempisp = req.getParameter("queryIsPayment");
        String pageIndex = req.getParameter("pageIndex");
        int queryProviderId = 0;
        if(temppid != null){
            queryProviderId = Integer.parseInt(temppid);
        }
        int queryIsPayment = 0;
        if(tempisp != null){
            queryIsPayment = Integer.parseInt(tempisp);
        }

        billServiceImpl billService = new billServiceImpl();
        providerServiceImpl providerService = new providerServiceImpl();

        //分页操作
        int pageSize = Constant.pageSize_pro;
        int currentPageNo = 1;
        if(pageIndex != null){ // 如果用户输入的页码不为空，进行解析并赋值
            currentPageNo = Integer.parseInt(pageIndex);
        }
        int totalCount = billService.getBillCount(queryProductName, queryProviderId, queryIsPayment);
        PageSupport pageSupport = new PageSupport();
        pageSupport.setPageSize(pageSize);
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setTotalCount(totalCount);
        int totalPageCount = pageSupport.getTotalPageCount();
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }

        List<Bill> billList = billService.getBillList(queryProductName, queryProviderId, queryIsPayment, currentPageNo, pageSize);
        List<Provider> providerList = providerService.getAllProviderList();

        req.setAttribute("providerList", providerList);
        req.setAttribute("billList", billList);
        req.setAttribute("queryProductName", queryProductName);
        req.setAttribute("queryProviderId", queryProviderId);
        req.setAttribute("queryIsPayment", queryIsPayment);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.setAttribute("totalCount", totalCount);
        req.getRequestDispatcher("billlist.jsp").forward(req, resp);
    }

    private void getBillById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException{
        String bid = req.getParameter("bid");
        if(bid != null){
            int id = Integer.parseInt(bid);
            billServiceImpl billService = new billServiceImpl();
            Bill bill = billService.getBillById(id);
            req.setAttribute("bill", bill);
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    private void addBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCounttemp = req.getParameter("productCount");
        String totalPricetemp = req.getParameter("totalPrice");
        String providerIdtemp = req.getParameter("providerId");
        String isPaymenttemp = req.getParameter("isPayment");
        BigDecimal productCount = null;
        if(productCounttemp != null){
            productCount = BigDecimal.valueOf(Double.parseDouble(productCounttemp));
        }
        BigDecimal totalPrice = null;
        if(productCounttemp != null){
            totalPrice = BigDecimal.valueOf(Double.parseDouble(totalPricetemp));
        }
        int isPayment = 0;
        if(isPaymenttemp != null){
            isPayment = Integer.valueOf(isPaymenttemp);
        }
        int providerId = 0;
        if(providerIdtemp != null){
            providerId = Integer.valueOf(providerIdtemp);
        }
        Bill bill = new Bill();
        bill.setBillCode(billCode);
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setIsPayment(isPayment);
        User creator = (User) req.getSession().getAttribute(Constant.USER_SESSION);
        bill.setCreatedBy(creator.getId());
        bill.setCreationDate(new Date());
        bill.setProviderId(providerId);
        billServiceImpl billService = new billServiceImpl();
        boolean flag = billService.addBill(bill);
        if(flag){
            resp.sendRedirect(req.getContextPath() + "/jsp/bill.do?method=query");
        }else{
            req.getRequestDispatcher("billadd.jsp").forward(req, resp);
        }
    }

    private void modifyBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String bid = req.getParameter("id");
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        String productUnit = req.getParameter("productUnit");
        String productCounttemp = req.getParameter("productCount");
        String totalPricetemp = req.getParameter("totalPrice");
        String providerIdtemp = req.getParameter("providerId");
        String isPaymenttemp = req.getParameter("isPayment");
        BigDecimal productCount = null;
        if(productCounttemp != null){
            productCount = BigDecimal.valueOf(Double.parseDouble(productCounttemp));
        }
        BigDecimal totalPrice = null;
        if(productCounttemp != null){
            totalPrice = BigDecimal.valueOf(Double.parseDouble(totalPricetemp));
        }
        int isPayment = 0;
        if(isPaymenttemp != null){
            isPayment = Integer.valueOf(isPaymenttemp);
        }
        int providerId = 0;
        if(providerIdtemp != null){
            providerId = Integer.valueOf(providerIdtemp);
        }

        Bill bill = new Bill();
        bill.setId(Integer.valueOf(bid));
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(productCount);
        bill.setTotalPrice(totalPrice);
        bill.setIsPayment(isPayment);
        User creator = (User) req.getSession().getAttribute(Constant.USER_SESSION);
        bill.setModifyBy(creator.getId());
        bill.setModifyDate(new Date());
        bill.setProviderId(providerId);

        billServiceImpl billService = new billServiceImpl();
        boolean flag = billService.modifyBill(bill);
        if(flag){
            resp.sendRedirect(req.getContextPath() + "/jsp/bill.do?method=query");
        }else{
            req.getRequestDispatcher("billmodify.jsp").forward(req, resp);
        }
    }

    private void delBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String bid = req.getParameter("bid");
        if(!StringUtils.isNullOrEmpty(bid)){
            int id = Integer.parseInt(bid);
            Map<String, String> resultMap = new HashMap<>();
            if(id <= 0){
                resultMap.put("delResult", "notExist");
            }else{
                billServiceImpl billService = new billServiceImpl();
                boolean flag = billService.delBill(id);
                if(flag){
                    resultMap.put("delResult", "true");
                }else{
                    resultMap.put("delResult", "false");
                }
            }
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        }
    }
}
