package com.lemon.auto.V8.util;

import com.lemon.auto.V8.pojo.Variable;

import java.util.*;
/*
* 参数化工具类
* */
public class VariableUtil {
   public static Map<String,String> variableNameAndValuesMap =new HashMap<>();
   public static List<Variable> variables =new ArrayList<>();
   static {
       //第一步加载表单里的数据依次将每行封装成对象，然后统一添加到集合
        List<Variable> list =ExcelUtil.load(PropertiesUtil.getExcelPath(),"变量",Variable.class);
       variables.addAll(list);
       //将变量及变量的值加载到map集合
        loadVariablesToMap();
   }
    /*
     * 替换参数里的变量
     * */
    public static String replaceVariables(String parmeter) {
        //取出所有的变量名
       Set<String> variableNames= variableNameAndValuesMap.keySet();
       for (String variableName:variableNames){
           //判断如果测试数据中出现变量名
           if (parmeter.contains(variableName)){
               parmeter= parmeter.replace(variableName,variableNameAndValuesMap.get(variableName));
           }
       }
       return parmeter;
    }
    /*
    * 遍历变量对象集合，将变量名和对应的变量值保存到map
    * */
    private static void loadVariablesToMap() {
        for (Variable variable:variables){
            String variableName =variable.getName();
            String variableValue=variable.getValue();
            variableNameAndValuesMap.put(variableName,variableValue);
        }
    }


}
