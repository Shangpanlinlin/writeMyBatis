package com.mybatis.sqlsession;

import domain.MapperXMLDetail;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {
    private Map<String, MapperXMLDetail> mapper;
    private Connection connection;

    public MapperProxy(Configuration configuration, Connection connection) {
        this.mapper = configuration.getMappers();
        this.connection = connection;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String qualifiedNameOfMethod = method.getDeclaringClass().getName()+"." + method.getName();
        MapperXMLDetail mapperXmlDetail = mapper.get(qualifiedNameOfMethod);
        if(mapperXmlDetail == null) {
            throw new IllegalArgumentException("参数错误");
        }
        //treat it as select
        return new Executor().execute(mapperXmlDetail.getSql(),(Integer) args[0],mapperXmlDetail.getResultType(),connection);
    }
}
