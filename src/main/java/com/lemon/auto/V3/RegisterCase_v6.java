package com.lemon.auto.V3;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
/*完成接口的测试
*
* */
public class RegisterCase_v6 {
    @Test(dataProvider = "datas")
    public void test1(String apiIdFromCase,String parameter){
        int [] rows={2,3,4,5};
        int [] cells= {1,3,4};
        //接口地址
        Object [][] datas= com.lemon.auto.V3.ExcelUtil.datas("src/test/resources/cases_v3.xls","接口信息",
                rows,cells);
        String url ="";
        String type ="";
        for (Object[] objects :datas){
              String apiIdFromRestc = objects[0].toString();
              if(apiIdFromCase.equals(apiIdFromRestc)){
                type =objects[1].toString();
                url=objects[2].toString();
                break;
            }
        }
        //需要参数
        Map<String,String> params = (Map<String, String>) JSONObject.parse(parameter);
        String result=HttpUtil.doService(url,type,params);
        System.out.println(result);
    }

    @DataProvider
    public Object [][] datas(){
        int [] rows = {2,3,4,5};
        int [] cells ={3,4};
        Object [][] datas= ExcelUtil.datas("src/test/resources/cases_v3.xls","用例",
                rows,cells);
        return datas;
    }
}

