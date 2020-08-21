package com.lemon.auto.V8.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties properties =new Properties();
    static {
        try {
            InputStream inputStream =new FileInputStream(new File("src/test/resources/confg.properties"));
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    * 获取用例文件路径
    * */
    public static String getExcelPath(){
        return properties.getProperty("excel.path");
    }
}
