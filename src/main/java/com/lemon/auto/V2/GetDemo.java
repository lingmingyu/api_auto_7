package com.lemon.auto.V2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetDemo {
    public static void main(String[] args) throws IOException {
        //提供接口地址
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
        System.out.println(HttpUtil.doGet(url,params));
    }

}
