package com.lemon.auto.V8.util;

import com.lemon.auto.V8.pojo.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableUtil {
   public static Map<String,String> variableNameAndValuesMap =new HashMap<>();
   public static List<Variable> variables =new ArrayList<>();
   static {
       //第一步加载表单里的数据依次将每行封装成对象，然后统一添加到集合

        List<Variable> list =ExcelUtil.load("","变量",Variable.class);
        variables.addAll(list);
   }
    /*
    * 替换参数里的变量
    * */
    public static String replaceVariables(String parameter) {

        return null;
    }
}
