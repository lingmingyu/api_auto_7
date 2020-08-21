package com.lemon.auto.V5;

import org.testng.annotations.DataProvider;

public class RegisterCase_v7 extends BaseProcessor {

    @DataProvider()
    public Object [][] datas(){
        String [] cellNames ={"CaseID","ApiId","Params"};
        Object [][] datas = CaseUtil.getCaseDatesByApiId("1",cellNames);
        return datas;
    }



}
