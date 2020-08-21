package test.cases;

import org.testng.annotations.DataProvider;
import test.pojo.BaseProcessor;
import test.util.CaseUtil;

public class RechargeCase extends BaseProcessor {
    @DataProvider()
    public Object [][] datas(){
        Object [][] datas = CaseUtil.getCaseDatesByApiId("3",cellNames);
        return datas;
    }

}
