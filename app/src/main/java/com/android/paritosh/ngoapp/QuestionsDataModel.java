package com.android.paritosh.ngoapp;

/**
 * Created by Paritosh on 6/1/2018.
 */

public class QuestionsDataModel {
    private String question;
    private String options[];
    private int type;
    private static int multiple = 1;
    private static int single = 0;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public QuestionsDataModel(String question, String[] options, int type) {
        this.question = question;
        this.options = options;
        this.type = type;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
