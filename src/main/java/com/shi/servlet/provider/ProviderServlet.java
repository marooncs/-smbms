package com.shi.servlet.provider;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.shi.Utiles.Constant;
import com.shi.Utiles.PageSupport;
import com.shi.pojo.Provider;
import com.shi.pojo.User;
import com.shi.service.provider.providerServiceImpl;
import com.shi.service.user.userServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProviderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method != null && method.equals("query")){
            this.query(req, resp);
        }
        if(method != null && method.equals("addProvider")){
            this.addProvider(req, resp);
        }
        if(method != null && method.equals("proCodeExist")){
            this.proCodeExist(req, resp);
        }
        if(method != null && method.equals("viewProvider")){
            this.getProviderById(req, resp, "providerview.jsp");
        }
        if(method != null && method.equals("modifyProvider")){
            this.getProviderById(req, resp, "providermodifiy.jsp");
        }
        if(method != null && method.equals("modifydata")){
            this.modifyProvider(req, resp);
        }
        if(method != null && method.equals("delProvider")){
            this.delProvider(req, resp);
        }
        if(method != null && method.equals("getProviderList")){
            this.getProviderList(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryProCode = req.getParameter("queryProCode");
        String queryProName = req.getParameter("queryProName");
        String pageIndex = req.getParameter("pageIndex");

        providerServiceImpl providerService = new providerServiceImpl();

        //分页操作
        int pageSize = Constant.pageSize_pro;
        int currentPageNo = 1;
        if(pageIndex != null){ // 如果用户输入的页码不为空，进行解析并赋值
            currentPageNo = Integer.parseInt(pageIndex);
        }
        int totalCount = providerService.getProviderCount(queryProCode, queryProName);
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

        List<Provider> providerList = providerService.getProviderList(queryProCode, queryProName, currentPageNo, pageSize);

        req.setAttribute("providerList", providerList);
        req.setAttribute("queryProCode", queryProCode);
        req.setAttribute("queryProName", queryProName);
        req.setAttribute("totalPageCount", totalPageCount);
        req.setAttribute("currentPageNo", currentPageNo);
        req.setAttribute("totalCount", totalCount);
        req.getRequestDispatcher("providerlist.jsp").forward(req, resp);
    }

    private void addProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proCode = req.getParameter("proCode");
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");

        Provider provider = new Provider();
        provider.setProCode(proCode);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        User creator = (User) req.getSession().getAttribute(Constant.USER_SESSION);
        provider.setCreatedBy(creator.getId());
        provider.setCreationDate(new Date());

        providerServiceImpl providerService = new providerServiceImpl();
        boolean flag = providerService.addProvider(provider);
        if(flag){
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        }else{
            req.getRequestDispatcher("provideradd.jsp").forward(req,resp);
        }
    }

    private void proCodeExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proCode = req.getParameter("proCode");
        Map<String, String> resultMap = new HashMap<>();
        if(StringUtils.isNullOrEmpty(proCode)){
            resultMap.put("proCode", "exist");
        }else{
            providerServiceImpl providerService = new providerServiceImpl();
            boolean flag = providerService.proCodeExist(proCode);
            if(flag){
                resultMap.put("proCode", "exist");
            }else{
                resultMap.put("proCode", "not-exist");
            }
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

    private void getProviderById(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
        String id = req.getParameter("pid");
        if(!StringUtils.isNullOrEmpty(id)){
            int pid = Integer.parseInt(id);
            providerServiceImpl providerService = new providerServiceImpl();
            Provider provider = providerService.getProviderById(pid);
            req.setAttribute("provider", provider);
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }

    private void modifyProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proName = req.getParameter("proName");
        String proContact = req.getParameter("proContact");
        String proPhone = req.getParameter("proPhone");
        String proAddress = req.getParameter("proAddress");
        String proFax = req.getParameter("proFax");
        String proDesc = req.getParameter("proDesc");
        String id = req.getParameter("proId");

        Provider provider = new Provider();
        Integer pid = Integer.valueOf(id);
        provider.setId(pid);
        provider.setProName(proName);
        provider.setProContact(proContact);
        provider.setProPhone(proPhone);
        provider.setProAddress(proAddress);
        provider.setProFax(proFax);
        provider.setProDesc(proDesc);
        User creator = (User) req.getSession().getAttribute(Constant.USER_SESSION);
        provider.setModifyBy(creator.getId());
        provider.setCreationDate(new Date());

        providerServiceImpl providerService = new providerServiceImpl();
        boolean flag = providerService.modifyProvider(provider);
        if(flag){
            resp.sendRedirect(req.getContextPath() + "/jsp/provider.do?method=query");
        }else{
            req.getRequestDispatcher("providermodifiy.jsp").forward(req, resp);
        }
    }

    private void delProvider(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(1);
        String pid = req.getParameter("pid");
        if(!StringUtils.isNullOrEmpty(pid)){
            System.out.println(2);
            int id = Integer.parseInt(pid);
            Map<String, String> resultMap = new HashMap<>();
            if(id <= 0){
                resultMap.put("delResult", "notExist");
            }else{
                providerServiceImpl providerService = new providerServiceImpl();
                boolean flag = providerService.delProvider(id);
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

    private void getProviderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        providerServiceImpl providerService = new providerServiceImpl();
        List<Provider> providerList = providerService.getAllProviderList();
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(providerList));
        writer.flush();
        writer.close();
    }

}
