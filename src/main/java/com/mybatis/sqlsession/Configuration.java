package com.mybatis.sqlsession;

import domain.MapperXMLDetail;

import java.util.LinkedHashMap;
import java.util.Map;

public class Configuration {
    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String, MapperXMLDetail> mappers = new LinkedHashMap<String, MapperXMLDetail>();

    @Override
    public String toString() {
        return "Configuration{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mappers=" + mappers +
                '}';
    }

    public Map<String, MapperXMLDetail> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, MapperXMLDetail> mappers) {
        this.mappers = mappers;
    }

    public String getDriver() {

        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
