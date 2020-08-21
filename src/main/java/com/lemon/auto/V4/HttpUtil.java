package com.lemon.auto.V4;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/*
* 接口调用工具类
* */
public class HttpUtil {
    /*
    * 以post方式处理接口调用
    * */
    public static String doPost(String url, Map<String,String> parmes){
        HttpPost post=new HttpPost(url);
        List<BasicNameValuePair> parameters =new ArrayList<>();
        Set<String> keys =parmes.keySet();
        for (String name:keys){
            String value=parmes.get(name);
            parameters.add(new BasicNameValuePair(name,value));

        }
        String result="";
        try {
            post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse= client.execute(post);
            int code =httpResponse.getStatusLine().getStatusCode();
            System.out.println(code);
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }
    /*
    * 以get方式完成接口调用
    * */
    public static String doGet(String url, Map<String,String> params){
        Set<String>keys=params.keySet();
        int mark =1;
        for (String name:keys){
            if (mark==1){
                url+=("?"+name+"="+params.get(name));
            }else {
                url+=("&"+name+"="+params.get(name));
            }
            mark++;

        }
        System.out.println(url);
        //指定接口提交的方式
        HttpGet get = new HttpGet(url);
        //发送请求,拿到相应数据
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse= null;
        String result="";
        try {
            httpResponse = httpClient.execute(get);
            int code =httpResponse.getStatusLine().getStatusCode();
            System.out.println(code);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }

    public static String doService(String url,String type,Map<String,String> params){
        String result ="";
        if ("post".equalsIgnoreCase(type)){
            result= HttpUtil.doPost(url,params);
        }else if ("get".equalsIgnoreCase(type)){
            result= HttpUtil.doGet(url,params);
        }
        return result;
    }

}
