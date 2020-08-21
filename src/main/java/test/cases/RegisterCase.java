package test.cases;

import org.testng.annotations.DataProvider;
import test.pojo.BaseProcessor;
import test.util.CaseUtil;

public class RegisterCase extends BaseProcessor {

    @DataProvider()
    public Object [][] datas(){
        Object [][] datas = CaseUtil.getCaseDatesByApiId("1",cellNames);
        return datas;
    }



}
