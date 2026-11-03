package com.expense.tracker.dtos;

public class SearchCriteriaParmeters {
    private String paramName;
    private String paramValue;
    private String paramOptions;
    
    public SearchCriteriaParmeters() {
    }
    public SearchCriteriaParmeters(String paramName, String paramValue, String paramOptions) {
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.paramOptions = paramOptions;
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamValue() {
        return paramValue;
    }
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
    public String getParamOptions() {
        return paramOptions;
    }
    public void setParamOptions(String paramOptions) {
        this.paramOptions = paramOptions;
    }

    
}
