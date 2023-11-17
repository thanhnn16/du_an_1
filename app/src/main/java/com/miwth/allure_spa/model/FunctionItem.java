package com.miwth.allure_spa.model;

public class FunctionItem {
    private int iconResId;
    private String functionName;

    public FunctionItem(int iconResId, String functionName) {
        this.iconResId = iconResId;
        this.functionName = functionName;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getFunctionName() {
        return functionName;
    }
}
