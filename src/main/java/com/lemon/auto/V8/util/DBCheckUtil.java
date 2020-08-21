package com.lemon.auto.V8.util;

import com.alibaba.fastjson.JSONObject;
import com.lemon.auto.V8.pojo.DBChecker;
import com.lemon.auto.V8.pojo.DBQueryResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBCheckUtil {
    /*根据脚本执行查询并返回查询结果
    * validateSql 需要执行得查询语句
    * */
    public static String doQuery(String validateSql) {
        //将脚本字符串封装成对象
        List<DBChecker> dbCheckers = JSONObject.parseArray(validateSql, DBChecker.class);
        List<DBQueryResult> dbQueryResults =new ArrayList<>();
        //循环遍历,取出sql脚本执行
        for (DBChecker dbChecker:dbCheckers){
            //拿到sql的编号
            String no =dbChecker.getNo();
            String sql =dbChecker.getSql();
            //执行查询，获取到结果
            Map<String,Object> columnLabelAndValues = JDBCUtil.query(sql);
            DBQueryResult dbQueryResult =new DBQueryResult();
            dbQueryResult.setNo(no);
            dbQueryResult.setColumenLabelAndValues(columnLabelAndValues);
            dbQueryResults.add(dbQueryResult);
        }

        return JSONObject.toJSONString(dbQueryResults);

    }

    public static void main(String[] args) {
        String validateSql="[{\"no\":\"1\",\"sql\":\"select count(*) as totalNum from member where mobilephone='19975275300'\"}]";
        List<DBChecker> dbCheckers = JSONObject.parseArray(validateSql, DBChecker.class);
        for (DBChecker dbChecker:dbCheckers){
            System.out.println(dbChecker);
        }

    }
}
