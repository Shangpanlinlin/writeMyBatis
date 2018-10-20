package com.frameworkwrite.test;

import com.frameworkwrite.UserMapper;
import com.mybatis.sqlsession.SqlSession;
import com.mybatis.sqlsession.SqlSessionFactory;
import com.mybatis.sqlsession.SqlSessionFactoryBuilder;
import domain.User;
import org.junit.Test;
import com.mybatis.io.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MybatisTest {
    @Test
    public void testSelectOne()  {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User users  = mapper.selectOne(11);
      //  System.out.println(user.toString());
        session.close();
    }

}
