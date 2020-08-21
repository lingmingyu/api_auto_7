package com.lemon.auto.V6.cases;

import com.lemon.auto.V6.pojo.BaseProcessor;
import com.lemon.auto.V6.util.CaseUtil;
import org.testng.annotations.DataProvider;

public class RechargeCase extends BaseProcessor {
    @DataProvider()
    public Object [][] datas(){
        String [] cellNames ={"CaseID","ApiId","Params","ExpectedResponseData"};
        Object [][] datas = CaseUtil.getCaseDatesByApiId("3",cellNames);
        return datas;
    }

}
