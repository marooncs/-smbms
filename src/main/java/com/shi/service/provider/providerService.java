package com.shi.service.provider;

import com.shi.pojo.Provider;

import java.util.List;
import java.util.Map;

public interface providerService {
    List<Provider> getProviderList(String proCode, String proName, int CurrentPageNo, int PageSize);

    List<Provider> getAllProviderList();

    int getProviderCount(String proCode, String proName);

    boolean addProvider(Provider provider);

    boolean proCodeExist(String proCode);

    Provider getProviderById(int id);

    boolean modifyProvider(Provider provider);

    boolean delProvider(int id);
}
