package com.lemon.auto.V8.pojo;

import java.util.Map;

/*
* 数据库查询结果实体类
* */
public class DBQueryResult {
    /*
    *脚本编号
    * */
    private String no;
    /*
    * 脚本执行查到数据，保存到map中(key保存的是字段名，value保存的是字段相应的数据)
    * */
    private Map<String,Object> columenLabelAndValues;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, Object> getColumenLabelAndValues() {
        return columenLabelAndValues;
    }

    public void setColumenLabelAndValues(Map<String, Object> columenLabelAndValues) {
        this.columenLabelAndValues = columenLabelAndValues;
    }
}
