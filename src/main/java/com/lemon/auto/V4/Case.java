package com.lemon.auto.V4;
/*
* 保存case的信息
* */
public class Case {
    private String CaseID;
    private String ApiId;
    private String Desc;
    private String Params;

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

    public Case(){}
    public Case(String CaseID,String ApiId,String Params){
        super();
        this.ApiId=ApiId;
        this.CaseID=CaseID;
        this.Params=Params;
    }
    @Override
    public String toString() {
        return "caseId="+CaseID+",apiId="+ApiId+",desc="+Desc+",params="+Params;
    }
}
