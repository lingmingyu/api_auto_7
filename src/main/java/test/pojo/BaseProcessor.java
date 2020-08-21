package test.pojo;

import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import test.util.*;

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
        if (preValidateSql!=null&&preValidateSql.trim().length()>0){
            //在接口调用前查询我们想要验证的字段
            String preValidateResult = DBCheckUtil.doQuery(preValidateSql);
            ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "PreValidateResult", preValidateResult));


        }
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
        if (afterValidateSql!=null&&afterValidateSql.trim().length()>0){
            //在接口调用后查询我们想要验证的字段
            String afterValidateResult = DBCheckUtil.doQuery(afterValidateSql);
            ExcelUtil.writeBackDatas.add(new WriteBackData("用例", caseId, "AfterValidateResult", afterValidateResult));

        }
    }
    @AfterSuite
    public void batchWriteBackDatas(){
        ExcelUtil.batchWriteBackDatas("src/test/resources/cases_v7.xls");
    }
}
