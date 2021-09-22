package com.shi.dao.provider;

import com.shi.Utiles.mybatis_utils;
import com.shi.dao.role.roleDao;
import com.shi.pojo.Provider;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class providerDaoImpl implements providerDao{

    public List<Provider> getProviderList(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        List<Provider> providerList = mapper.getProviderList(map);
        sqlSession.close();
        return providerList;
    }

    public List<Provider> getAllProviderList() {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        List<Provider> providerList = mapper.getAllProviderList();
        sqlSession.close();
        return providerList;
    }

    public int getProviderCount(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        int providerCount = mapper.getProviderCount(map);
        sqlSession.close();
        return providerCount;
    }

    public int addProvider(Provider provider) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        int i = mapper.addProvider(provider);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public Provider getProviderByCode(String proCode) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        Provider provider = mapper.getProviderByCode(proCode);
        sqlSession.close();
        return provider;
    }

    public Provider getProviderById(int id) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        Provider provider = mapper.getProviderById(id);
        sqlSession.close();
        return provider;
    }

    public int modifyProvider(Provider provider) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        int i = mapper.modifyProvider(provider);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public int delProvider(int id) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        providerDao mapper = sqlSession.getMapper(providerDao.class);
        int i = mapper.delProvider(id);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
