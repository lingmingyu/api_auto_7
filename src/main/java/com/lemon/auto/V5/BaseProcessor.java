package com.lemon.auto.V5;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/*接口测试统一处理类
*
* */
public class BaseProcessor {
    @Test(dataProvider = "datas")
    //拿到用例编号，拿到api编号，拿到测试数据
    public void test(String caseId,String apiId,String parameter) {
        //url
        String url = RestUtil.getUrlByApiId(apiId);
        //type
        String type = RestUtil.getTypeByApiId(apiId);
        //需要参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameter);
        //调用doservice方法完成接口调用，拿到接口响应报文
        String result = HttpUtil.doService(url, type, params);
        System.out.println(result);
        //保存回写数据对象
        ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "ActualResponseData", result));
    }
    @AfterSuite
    public void batchWriteBackDatas(){
        ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v5.xls");
    }
}
