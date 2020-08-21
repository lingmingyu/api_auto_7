package com.lemon.auto.V4;

import com.alibaba.fastjson.JSONObject;
import com.lemon.auto.V3.ExcelUtil;
import com.lemon.auto.V3.HttpUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.jws.Oneway;
import java.util.Map;

/*完成接口的测试
*
* */
public class RegisterCase_v7 {
    @Test(dataProvider = "datas")
    public void test1(String apiId,String parameter,String expectedResponseData){
        //url
        String url=RestUtil.getUrlByApiId(apiId);
        //type
        String type =RestUtil.getTypeByApiId(apiId);
        //需要参数
        Map<String,String> params = (Map<String, String>) JSONObject.parse(parameter);
        String result=HttpUtil.doService(url,type,params);
        System.out.println(result);
    }

    @DataProvider
    public Object [][] datas(){
        String [] cellNames ={"ApiId","Params"};
        Object [][] datas= CaseUtil.getCaseDatesByApiId("1",cellNames);
        return datas;
    }

    public static void main(String[] args) {
        String [] cellName ={"ApiId"};
        Object [][] datas= CaseUtil.getCaseDatesByApiId("1",cellName);
        for (Object [] objects :datas){
            for (Object object :objects){
                System.out.println("["+object+"]");
            }
            System.out.println();
        }
    }

}

