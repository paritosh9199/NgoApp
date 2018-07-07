package com.android.paritosh.ngoapp;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paritosh on 6/3/2018.
 */

public class RadioUtils  {
    public RadioButton radioButton;
    public String answer;
    public ViewGroup vg;
    int position;
    private SurveyActivity sa;

    private RadioGroup lastCheckedRadioGroup = null;

    public void setRadioExclusiveClick(ViewGroup parent, final int position) {
        this.position = position;
        final List<RadioButton> radios = getRadioButtons(parent);
        try {
            for (RadioButton radio : radios) {

                radio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton r = (RadioButton) v;
                        r.setChecked(true);
                        for (RadioButton r2 : radios) {
                            if (r2.getId() != r.getId()) {
                                r2.setChecked(false);
                            }
                        }

                    }
                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<RadioButton> getRadioButtons(ViewGroup parent) {
        List<RadioButton> radios = new ArrayList<RadioButton>();
        for (int i=0;i < parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if (v instanceof RadioButton) {
                radios.add((RadioButton) v);
            } else if (v instanceof ViewGroup) {
                List<RadioButton> nestedRadios = getRadioButtons((ViewGroup) v);
                radios.addAll(nestedRadios);
            }
        }
        return radios;
    }
}