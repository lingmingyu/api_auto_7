package com.lemon.auto.V3;

import com.alibaba.fastjson.JSONObject;
import com.lemon.auto.V2.HttpUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*完成接口的测试
*
* */
public class RegisterCase_v5 {
    //密码为空
    //账号为空
    //密码
    //正确账号密码
    @Test(dataProvider = "datas")
    public void test1(String parameter){
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        Map<String,String> params= (Map<String, String>) JSONObject.parse(parameter);
        System.out.println(HttpUtil.doPost(url,params));
//        RegisterParam registerParam= JSONObject.parseObject(parameter,RegisterParam.class);
//        System.out.println(registerParam);

    }

    @DataProvider
   public Object [][] datas(){
        int [] rows = {2,3,4,5};
        int [] cells ={6};
        Object [][] datas= ExcelUtil.datas("src/test/resources/cases_v2.xls","用例",
                rows,cells);
        return datas;
    }

}
