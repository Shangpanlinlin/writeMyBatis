package com.mybatis.sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;
    private Connection connection;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        connection = DataSourceUtil.getConnection(configuration);
    }


    // 用于创建代理对象，
    public <T> T getMapper(Class<T> mapperClass) {

        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass} , new MapperProxy(configuration,connection));
    }

    //用于释放资源
    public void close() {

    }
}
