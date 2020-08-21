package com.lemon.auto.V8.pojo;

import com.alibaba.fastjson.JSONObject;
import com.lemon.auto.V8.util.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/*接口测试统一处理类
*
* */
public class BaseProcessor {
    public String [] cellNames ={"CaseID","ApiId","Params","ExpectedResponseData","PreValidateSql","AfterValidateSql"};
    @Test(dataProvider = "datas")
    //拿到用例编号，拿到api编号，拿到测试数据
    public void test(String caseId,String apiId,String parameter,String expectedResponseData
            ,String preValidateSql,String afterValidateSql) {
        //执行接口调用前数据验证
        if (preValidateSql!=null&&preValidateSql.trim().length()>0){
            preValidateSql= VariableUtil.replaceVariables(preValidateSql);
            //在接口调用前查询我们想要验证的字段
            String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
            ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult));


        }
        //根据apiid获取接口地址
        String url = RestUtil.getUrlByApiId(apiId);
        //根据apiid获取接口提交类型
        String type = RestUtil.getTypeByApiId(apiId);
        //替换测试数据中的所有变量
        parameter= VariableUtil.replaceVariables(parameter);
        //将参数解析到map
        Map<String, String> params = (Map<String, String>) JSONObject.parse(parameter);
        //调用doservice方法完成接口调用，拿到接口响 应报文
        String acualResponseData = HttpUtil.doService(url, type, params);
        //断言比较期望值和实际测试结果
        acualResponseData= AssertUtil.assertEquals(acualResponseData,expectedResponseData);
        //保存回写数据对象
        ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "ActualResponseData", acualResponseData));
        //执行接口调用后数据验证
        if (afterValidateSql!=null&&afterValidateSql.trim().length()>0){
            afterValidateSql= VariableUtil.replaceVariables(afterValidateSql);
            //在接口调用后查询我们想要验证的字段
            String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
            ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "AfterValidateResult", afterValidateResult));

        }
    }
    /*
    * 批量回写数据
    * */
    @AfterSuite
    public void batchWriteBackDatas(){
        ExcelUtil.batchWriteBackDatas(PropertiesUtil.getExcelPath());
    }
}
