package com.lemon.auto.V8.cases;

import com.lemon.auto.V8.pojo.BaseProcessor;
import com.lemon.auto.V8.util.CaseUtil;
import org.testng.annotations.DataProvider;

public class RechargeCase extends BaseProcessor {
    @DataProvider()
    public Object [][] datas(){
        Object [][] datas = CaseUtil.getCaseDatesByApiId("3",cellNames);
        return datas;
    }

}
