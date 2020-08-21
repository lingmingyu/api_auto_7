package com.lemon.auto.V5;

import java.util.ArrayList;
import java.util.List;

public class RestUtil {
    public  static List<Rest> rests =new ArrayList<Rest>();
    static {
        ExcelUtil.load("src/test/resources/cases_v4.xls","接口信息", Rest.class);
    }
    /*根据接口编号获取接口地址
     *
     * */
    public static String getUrlByApiId(String apiId){
        for (Rest rest:rests){
            if (rest.getApiId().equals(apiId)){
                return rest.getUrl();
            }
        }
        return "";
    }



    /*根据接口编号获取接口请求类型
     *apiId:接口编号
     * */
    public static String getTypeByApiId(String apiId) {
        for (Rest rest :rests){
            if (rest.getApiId().equals(apiId)){
                return rest.getType();
            }
        }
        return "";
    }
}
