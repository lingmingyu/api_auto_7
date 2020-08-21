package com.lemon.auto.V5;

import org.testng.annotations.DataProvider;

public class LoginCase extends BaseProcessor {

    @DataProvider
    public Object [][] datas(){
        String [] cellNames ={"CaseID","ApiId","Params"};
        Object [][] datas = CaseUtil.getCaseDatesByApiId("2",cellNames);
        return datas;
    }
}
