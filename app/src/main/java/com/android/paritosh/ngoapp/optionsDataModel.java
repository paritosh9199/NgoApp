package com.android.paritosh.ngoapp;

/**
 * Created by Paritosh on 6/1/2018.
 */

public class optionsDataModel {
    private int type;
    private String option;

    public optionsDataModel(int type, String option) {
        this.type = type;
        this.option = option;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
