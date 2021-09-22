package com.shi.Utiles;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class mybatis_utils {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String mybatis_config = "Mybatis_config.xml";
        try {
            InputStream in = Resources.getResourceAsStream(mybatis_config);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
