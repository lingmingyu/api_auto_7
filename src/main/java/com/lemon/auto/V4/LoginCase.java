package com.lemon.auto.V4;

import com.alibaba.fastjson.JSONObject;
import com.lemon.auto.V3.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
/*
* 对登录接口测试类
* */
public class LoginCase {
    @Test(dataProvider = "datas")
    public void test1(String apiId,String parameter){
        //url
        String url=RestUtil.getUrlByApiId(apiId);
        //type
        String type =RestUtil.getTypeByApiId(apiId);
        //需要参数
        Map<String,String> params = (Map<String, String>) JSONObject.parse(parameter);
        String result= HttpUtil.doService(url,type,params);
        System.out.println(result);
    }

    @DataProvider
    public Object [][] datas(){
        String [] cellNames ={"ApiId","Params"};
        Object [][] datas= CaseUtil.getCaseDatesByApiId("2",cellNames);
        return datas;
    }
}
