package com.lemon.auto.V1;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PostDemo {
    public static void main(String[] args) throws IOException {
        //指定接口地址
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        //指定接口请求方式：post
        HttpPost post =new HttpPost(url);
        //准备测试数据
        String ReturnURL = "";
        String userName ="710600200072001";
        String password ="199705";
        String orgId ="";
        List<BasicNameValuePair>  parmeters =new ArrayList<>();
        parmeters.add(new BasicNameValuePair("ReturnURL",ReturnURL));
        parmeters.add(new BasicNameValuePair("userName",userName));
        parmeters.add(new BasicNameValuePair("password",password));
        parmeters.add(new BasicNameValuePair("orgId",orgId));
        post.setEntity(new UrlEncodedFormEntity(parmeters,"utf-8"));
        //发送请求，获取接口响应信息(状态码，响应报文，或者某些特殊的响应头数据)
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse =client.execute(post);
        //状态码
        int code= httpResponse.getStatusLine().getStatusCode();
        System.out.println(code);
        //响应报文
        String result = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

    }


}
