package com.lemon.auto.V1;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GetDemo {
    public static void main(String[] args) throws IOException {
        //提供接口地址
        String url ="http://120.78.128.25:8765/futureloan/mvc/api/member/register";;
        //准备测试数据
        String moblephone ="19975275300";
        String pwd ="37710";
        url+=("?Mobilephone="+moblephone+"&pwd="+pwd);
        //指定接口提交的方式
        HttpGet  get =new HttpGet(url);
        //发送请求，拿到响应数据
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse=httpClient.execute(get);
        int code =httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        String result=EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);


    }
}
