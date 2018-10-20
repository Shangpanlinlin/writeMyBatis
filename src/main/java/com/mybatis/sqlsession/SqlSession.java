package com.mybatis.sqlsession;

import com.frameworkwrite.UserMapper;

public interface SqlSession {
     <T> T getMapper(Class<T> mapperClass);

    void close();
}
