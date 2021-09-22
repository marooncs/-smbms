package com.shi.dao.provider;

import com.shi.pojo.Provider;

import java.util.List;
import java.util.Map;

public interface providerDao {
    List<Provider> getProviderList(Map map);

    List<Provider> getAllProviderList();

    int getProviderCount(Map map);

    int addProvider(Provider provider);

    Provider getProviderByCode(String proCode);

    Provider getProviderById(int id);

    int modifyProvider(Provider provider);

    int delProvider(int id);

}
