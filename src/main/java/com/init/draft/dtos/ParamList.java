package com.expense.tracker.dtos;

public class ParamList {
    private String paramName;
    private String paramValue;
    private Class<?> type;

    public ParamList(String paramName, String paramValue, Class<?> type) {
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.type = type;
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

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
