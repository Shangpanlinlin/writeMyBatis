package com.mybatis.sqlsession;

import com.mybatis.util.ConfigurationReader;
import org.dom4j.DocumentException;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) {
        //构建SqlSessionFacotory
        Configuration configuration = null;
        try {
            configuration = ConfigurationReader.getConfiguration(in);
            return new DefaultSqlSessionFactory(configuration);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
