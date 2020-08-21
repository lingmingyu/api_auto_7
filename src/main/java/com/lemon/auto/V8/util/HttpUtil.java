package com.lemon.auto.V8.util;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class HttpUtil {
    public static Map<String,String> cookies =new HashMap<>();
    public static void main(String[] args) {
        String url = "http://www.xdwy.com.cn/learning/loginController.do?checkuser";
        Map<String,String> params = new HashMap<>();
        String ReturnURL = "";
        String userName ="710600200072001";
        String password ="19970530";
        String orgId ="";
        params.put("ReturnURL",ReturnURL);
        params.put("userName",userName);
        params.put("password",password);
        params.put("orgId",orgId);
        System.out.println(HttpUtil.doPost(url,params));

    }
    /*
     *以post方式处理接口类
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
            addCookieInRequestHeaderBeforeRequest(post);
            HttpResponse httpResponse= client.execute(post);
            getAndStoreCookiesFromResponseHeader(httpResponse);
            int code =httpResponse.getStatusLine().getStatusCode();
            result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("code=["+code+"],result=["+result+"]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;


    }

    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
       String jessionId=  cookies.get("JSESSIONID");
       if (jessionId!=null){
           request.addHeader("Cookie",jessionId);
       }
    }

    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        //从响应头里取出名字为Set-cookie的响应他头
        Header setCookieheader = httpResponse.getFirstHeader("Set-Cookie");
        //如果不为空
        if (setCookieheader!=null){
            //取出此响应头的值
            String cookiePairsString=setCookieheader.getValue();
            if (cookiePairsString!=null&&cookiePairsString.trim().length()>0){
                //以";"来切分
                String [] cookiePairs = cookiePairsString.split(";");
                if (cookiePairs!=null){
                  for (String cookiepair:cookiePairs){
                      //如果包含JSESSIONID，则意味着响应头里有会话id这个数据
                        if (cookiepair.contains("JSESSIONID")){
                            //保存到map
                            cookies.put("JSESSIONID",cookiepair);
                            System.out.println(cookiepair);
                        }
                  }
                }

            }
        }

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
            addCookieInRequestHeaderBeforeRequest(get);
            httpResponse = httpClient.execute(get);
            getAndStoreCookiesFromResponseHeader(httpResponse);
            int code =httpResponse.getStatusLine().getStatusCode();
            result= EntityUtils.toString(httpResponse.getEntity());
            System.out.println("code=["+code+"],result=["+result+"]");
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
