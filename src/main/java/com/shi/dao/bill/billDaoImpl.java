package com.shi.dao.bill;

import com.shi.Utiles.mybatis_utils;
import com.shi.pojo.Bill;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class billDaoImpl implements billDao{

    public int getBillCount(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        billDao mapper = sqlSession.getMapper(billDao.class);
        int i = mapper.getBillCount(map);
        sqlSession.close();
        return i;
    }

    public List<Bill> getBillList(Map map) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        billDao mapper = sqlSession.getMapper(billDao.class);
        List<Bill> billList = mapper.getBillList(map);
        return billList;
    }

    public Bill getBillById(int id) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        billDao mapper = sqlSession.getMapper(billDao.class);
        Bill bill = mapper.getBillById(id);
        sqlSession.close();
        return bill;
    }

    public int addBill(Bill bill) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        billDao mapper = sqlSession.getMapper(billDao.class);
        int i = mapper.addBill(bill);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public int modifyBill(Bill bill) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        billDao mapper = sqlSession.getMapper(billDao.class);
        int i = mapper.modifyBill(bill);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    public int delBill(int id) {
        SqlSession sqlSession = mybatis_utils.getSqlSession();
        billDao mapper = sqlSession.getMapper(billDao.class);
        int i = mapper.delBill(id);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
