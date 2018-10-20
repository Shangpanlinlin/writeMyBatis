package com.mybatis.sqlsession;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Date;

public class Executor {
    protected  <E> E execute(String sql, int args, String resultType, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            Class resultClazz = Class.forName(resultType);
            ps.setInt(1,args);
            ResultSet result = ps.executeQuery();
            result.next();
            E obj = (E) resultClazz.newInstance();
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            for(int i=1; i <= resultSetMetaData.getColumnCount(); i++){
                 this.setProperty(obj, resultSetMetaData.getColumnName(i), result.getObject(i));
            }
            System.out.println(obj);
            return obj;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <E> void setProperty(E obj, String catalogName, Object value) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if(field.getName().equals(catalogName)){
                field.setAccessible(true);
                if(value instanceof Date)
                    field.set(obj,value.toString());
                else
                    field.set(obj, value);
            }

        }
    }
}
