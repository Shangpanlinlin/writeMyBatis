package com.mybatis.util;

import com.mybatis.io.Resources;
import com.mybatis.sqlsession.Configuration;
import domain.MapperXMLDetail;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationReader {

    public static final Configuration getConfiguration(InputStream path) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document =  reader.read(path);
        Configuration configuration = new Configuration();

        Element element = document.getRootElement();
        List<Element> elementList = element.selectNodes("//property");
        for (Element element1 : elementList) {
            String name = element1.attributeValue("name");
            System.out.println(name);
            if("driver".equals(name)){
                configuration.setDriver(element1.attributeValue("value"));
            }
            if("url".equals(name)){
                configuration.setUrl(element1.attributeValue("value"));
            }
            if("username".equals(name)){
                configuration.setUsername(element1.attributeValue("value"));
            }
            if("password".equals(name)){
                configuration.setPassword(element1.attributeValue("value"));
            }
        }

        //继续解析mappers
        List<Element> elementsMapper = element.selectNodes("//mappers/mapper");
        for (Element ele : elementsMapper) {
            //遍历每一个mapper元素，

            Attribute attribute = ele.attribute("resource");  //获取element元素的属性名为resource的属性对象
            if(attribute == null){
                //annotationed parse applied

            }else{
                String pathOfMapper = attribute.getValue(); // 获取元素属性名字为resource的属性的值
                Map<String, MapperXMLDetail> mapper = loadMapperConfiguration(pathOfMapper);
                configuration.getMappers().putAll(mapper);
            }

        }
        return configuration;
    }

    private static Map<String, MapperXMLDetail> loadMapperConfiguration(String pathOfMapper) throws DocumentException {
        Map<String, MapperXMLDetail> mappers = new LinkedHashMap<String, MapperXMLDetail>();
        InputStream in = Resources.getResourceAsStream(pathOfMapper);
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element root = document.getRootElement();
        Element elementOfMapper = (Element) root.selectNodes("//mapper").get(0);
        String namespaceOfMapper = elementOfMapper.attributeValue("namespace");
        List<Element> elementsofSelect = root.selectNodes("//mapper/select");
        for (Element element : elementsofSelect) {
             MapperXMLDetail mapper = new MapperXMLDetail();
             String id = namespaceOfMapper+ "." + element.attributeValue("id");
             mapper.setNameId(id);
             String resultType = element.attributeValue("resultType");
             String sql = element.getTextTrim();
             mapper.setResultType(resultType);
             mapper.setSql(sql);
             mappers.put(mapper.getNameId(),mapper);
        }
        return  mappers;
    }

    /*
    * <mapper namespace="com.frameworkwrite.UserMapper">
    <select id="selectOne" resultType="domain.User">
     select * from tab_user where uid = #{id}
  </select>
</mapper>
    *
    * */

    public static void main(String[] args) {
        try {
            getConfiguration(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
