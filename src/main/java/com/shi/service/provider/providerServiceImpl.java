package com.shi.service.provider;

import com.shi.dao.provider.providerDao;
import com.shi.dao.provider.providerDaoImpl;
import com.shi.pojo.Provider;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class providerServiceImpl implements providerService{
    private providerDao providerDao;
    public providerServiceImpl(){
        providerDao = new providerDaoImpl();
    }

    public List<Provider> getProviderList(String proCode, String proName, int CurrentPageNo, int PageSize){
        Map<String, Object> map = new HashMap<>();
        map.put("proCode", proCode);
        map.put("proName", proName);
        map.put("CurrentPageNo", (CurrentPageNo - 1) * PageSize);
        map.put("PageSize",PageSize);
        List<Provider> providerList = providerDao.getProviderList(map);
        return providerList;
    }

    public List<Provider> getAllProviderList() {
        return providerDao.getAllProviderList();
    }

    public int getProviderCount(String proCode, String proName) {
        Map<String, String> map = new HashMap<>();
        map.put("proCode", proCode);
        map.put("proName", proName);
        int providerCount = providerDao.getProviderCount(map);
        return providerCount;
    }

    public boolean addProvider(Provider provider) {
        boolean flag = false;
        int rows = providerDao.addProvider(provider);
        if(rows > 0){
            flag = true;
        }
        return flag;
    }

    public boolean proCodeExist(String proCode) {
        boolean flag = false;
        Provider provider = providerDao.getProviderByCode(proCode);
        if(provider != null){
            flag = true;
        }
        return flag;
    }

    public Provider getProviderById(int id) {
        return providerDao.getProviderById(id);
    }

    public boolean modifyProvider(Provider provider) {
        boolean flag = false;
        int rows = providerDao.modifyProvider(provider);
        if(rows > 0){
            flag = true;
        }
        return flag;
    }

    public boolean delProvider(int id) {
        boolean flag = false;
        int rows = providerDao.delProvider(id);
        if(rows > 0){
            flag = true;
        }
        return flag;
    }
}
