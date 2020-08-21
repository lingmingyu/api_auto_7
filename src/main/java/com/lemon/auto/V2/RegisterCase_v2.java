package com.lemon.auto.V2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/*完成接口的测试
*
* */
public class RegisterCase_v2 {
    //密码为空
    //账号为空
    //密码
    //正确账号密码
    @Test(dataProvider = "datas")
    public void test1(String userName,String password){
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        String ReturnURL = "";
        String orgId ="";
        Map<String,String> params =new HashMap<>();
        params.put("ReturnURL",ReturnURL);
        params.put("userName",userName);
        params.put("password",password);
        params.put("orgId",orgId);
        System.out.println(HttpUtil.doPost(url,params));
    }
    @DataProvider
   public Object [][] datas(){
        Object [][] datas={
                         {"710600200072001",""},
                         {"","199705"},
                         {"710600200072001","710600200072001"},
                         {"710600200072001","199705"}
           };
        return datas;
    }
}
