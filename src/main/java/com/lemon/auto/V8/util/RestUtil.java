package com.lemon.auto.V8.util;

import com.lemon.auto.V8.pojo.Rest;

import java.util.ArrayList;
import java.util.List;

public class RestUtil {
    public  static List<Rest> rests =new ArrayList<Rest>();
    static {
        List<Rest> list=ExcelUtil.load(PropertiesUtil.getExcelPath(),"接口信息", Rest.class);
        rests.addAll(list);
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
