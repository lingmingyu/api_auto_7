package com.lemon.auto.V6.cases;
/*保存case的信息
*
* */
public class Case {
    private String CaseID;
    private String ApiId;
    private String Desc;
    private String Params;
    private String ExpectedResponseData;
    private String ActualResponseData;

    public String getCaseID() {
        return CaseID;
    }

    public void setCaseID(String caseID) {
        CaseID = caseID;
    }

    public String getApiId() {
        return ApiId;
    }

    public void setApiId(String apiId) {
        ApiId = apiId;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getParams() {
        return Params;
    }

    public void setParams(String params) {
        Params = params;
    }

    public String getExpectedResponseData() {
        return ExpectedResponseData;
    }

    public void setExpectedResponseData(String expectedResponseData) {
        ExpectedResponseData = expectedResponseData;
    }

    public String getActualResponseData() {
        return ActualResponseData;
    }

    public void setActualResponseData(String actualResponseData) {
        ActualResponseData = actualResponseData;
    }

    @Override
    public String toString() {
        return "CaseId="+CaseID+",ApiId="+ApiId+",Desc="+Desc+",Params="+Params;
    }
}
