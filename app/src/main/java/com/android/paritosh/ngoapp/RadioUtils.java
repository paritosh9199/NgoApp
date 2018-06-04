package com.android.paritosh.ngoapp;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paritosh on 6/3/2018.
 */

public class RadioUtils {

    public static void setRadioExclusiveClick(ViewGroup parent) {
        final List<RadioButton> radios = getRadioButtons(parent);

        for (RadioButton radio: radios) {
            radio.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    RadioButton r = (RadioButton) v;
                    r.setChecked(true);
                    for (RadioButton r2:radios) {
                        if (r2.getId() != r.getId()) {
                            r2.setChecked(false);
                        }
                    }

                }
            });
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