package com.lemon.auto.V8.util;

import org.testng.Assert;

public class AssertUtil {
    /*自定义类库:断言比较实际测试结果跟期望值是否一样
    * */
    public static String assertEquals(String acualResponseData, String expectedResponseData) {
        String resulrt= "通过";
        try {
            Assert.assertEquals(acualResponseData,expectedResponseData);
        }catch (Throwable e){
            resulrt=acualResponseData;
        }
        return resulrt;
    }
}
