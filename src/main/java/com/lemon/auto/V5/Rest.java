package com.lemon.auto.V5;

public class Rest {
    private String apiId;
    private String apiName;
    private String type;
    private String url;
    private String ExpectedResponseData;
    private String ActualResponseData;


    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "apiId="+apiId+",apiName="+apiName+
                ",type="+type+",url="+url+",ExpectedResponseData="+
                ExpectedResponseData+",ActualResponseData="+ActualResponseData;
    }
    }

