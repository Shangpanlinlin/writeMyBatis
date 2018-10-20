package com.mybatis.sqlsession;

public class DefaultSqlSessionFactory implements  SqlSessionFactory {
    private final Configuration configuration;
    protected DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    //实现操作数据库核心对象的方法
    public SqlSession openSession() {
        //获取SqlSession就新建一个SqlSession实例返回，把包含有mapper信息的configuration传递进去
        return new DefaultSqlSession(configuration);
    }
}
