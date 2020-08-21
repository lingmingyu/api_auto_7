package com.lemon.auto.V2;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/*完成接口的测试
*
* */
public class RegisterCase_v1 {
    //密码为空
    //账号为空
    //密码
    //正确账号密码
    @Test
    public void test1(){
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        String ReturnURL = "";
        String userName ="710600200072001";
        String password ="";
        String orgId ="";
        Map<String,String> params =new HashMap<>();
        params.put("ReturnURL",ReturnURL);
        params.put("userName",userName);
        params.put("password",password);
        params.put("orgId",orgId);
        System.out.println(HttpUtil.doPost(url,params));
    }
    @Test
    public void test2(){
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        String ReturnURL = "";
        String userName ="";
        String password ="199705";
        String orgId ="";
        Map<String,String> params =new HashMap<>();
        params.put("ReturnURL",ReturnURL);
        params.put("userName",userName);
        params.put("password",password);
        params.put("orgId",orgId);
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void test3(){
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        String ReturnURL = "";
        String userName ="710600200072001";
        String password ="710600200072001";
        String orgId ="";
        Map<String,String> params =new HashMap<>();
        params.put("ReturnURL",ReturnURL);
        params.put("userName",userName);
        params.put("password",password);
        params.put("orgId",orgId);
        System.out.println(HttpUtil.doPost(url,params));
    }

    @Test
    public void test4(){
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        String ReturnURL = "";
        String userName ="710600200072001";
        String password ="199705";
        String orgId ="";
        Map<String,String> params =new HashMap<>();
        params.put("ReturnURL",ReturnURL);
        params.put("userName",userName);
        params.put("password",password);
        params.put("orgId",orgId);
        System.out.println(HttpUtil.doPost(url,params));
    }
}
