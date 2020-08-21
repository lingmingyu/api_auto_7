package com.lemon.auto.V6.pojo;

import com.alibaba.fastjson.JSONObject;
import com.lemon.auto.V6.util.AssertUtil;
import com.lemon.auto.V6.util.ExcelUtil;
import com.lemon.auto.V6.util.HttpUtil;
import com.lemon.auto.V6.util.RestUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/*接口测试统一处理类
*
* */
public class BaseProcessor {
    @Test(dataProvider = "datas")
    //拿到用例编号，拿到api编号，拿到测试数据
    public void test(String caseId,String apiId,String parameter,String expectedResponseData) {
        //url
        String url = RestUtil.getUrlByApiId(apiId);
        //type
        String type = RestUtil.getTypeByApiId(apiId);
        //需要参数
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameter);
        //调用doservice方法完成接口调用，拿到接口响 应报文
        String acualResponseData = HttpUtil.doService(url, type, params);
        acualResponseData= AssertUtil.assertEquals(acualResponseData,expectedResponseData);
        //保存回写数据对象
        ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "ActualResponseData", acualResponseData));
    }
    @AfterSuite
    public void batchWriteBackDatas(){
        ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v6.xls");
    }
}
