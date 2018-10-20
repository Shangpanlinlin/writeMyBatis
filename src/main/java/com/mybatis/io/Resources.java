package com.mybatis.io;

import java.io.InputStream;

public class Resources {
    public static InputStream getResourceAsStream(String s) {
        return Resources.class.getClassLoader().getResourceAsStream(s);
    }
}
