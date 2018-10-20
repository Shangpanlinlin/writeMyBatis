package domain;
//ConfigurationReader读取配置文件会急需解析mappers元素下面的所有mapper,每一个 mapper.xml会解析出很多个 Mapper对象，
public class MapperXMLDetail {
    private String nameId; // namespace  + id
    private String sql; //sql statement
    private String resultType; // qualified name of result type;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "Mapper{" +
                "nameId='" + nameId + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }
}
