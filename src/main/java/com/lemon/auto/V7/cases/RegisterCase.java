package com.lemon.auto.V7.cases;

import com.lemon.auto.V7.pojo.BaseProcessor;
import com.lemon.auto.V7.util.CaseUtil;
import org.testng.annotations.DataProvider;

public class RegisterCase extends BaseProcessor {

    @DataProvider()
    public Object [][] datas(){
        Object [][] datas = CaseUtil.getCaseDatesByApiId("1",cellNames);
        return datas;
    }



}
