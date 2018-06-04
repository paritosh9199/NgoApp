package com.android.paritosh.ngoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paritosh on 6/1/2018.
 */

public class optionsAdapter extends ArrayAdapter<optionsDataModel> {
    RadioGroup rg;
    int type;
    public optionsAdapter(@NonNull Context context, @NonNull List<optionsDataModel> objects,int type) {
        super(context, 0, objects);
        this.type=type;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            //LayoutInflater.from(getContext()).inflate(R.layout.books_display_layout,null);
            newView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.srv_option_obj, parent, false);
        }

        final optionsDataModel item = getItem(position);




        TextView option = newView.findViewById(R.id.tv_optionTextView);

        if(type==0){
            //single
            //cb.setVisibility(View.GONE);
            option.setText(item.getOption());
            Log.i("opTV0",item.getOption());
        }
        if(type==1){
            //multiple
            //rb.setVisibility(View.GONE);
            option.setText(item.getOption());
            Log.i("opTV1",item.getOption());

        }

        /*
        final View finalNewView = newView;
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //CheckBox cb = newView.findViewById(R.id.cb_optionCheck);
                //RadioButton rb = newView.findViewById(R.id.rb_optionRadio);
                TextView option = finalNewView.findViewById(R.id.tv_optionTextView);

                if(type==0){
                    //single
                    //cb.setVisibility(View.GONE);
                    option.setText(item.getOption());
                    //Log.i("opTV0",item.getOption());
                }
                if(type==1){
                    //multiple
                    //rb.setVisibility(View.GONE);
                    option.setText(item.getOption());
                    //Log.i("opTV1",item.getOption());
                }
            }
        });*/

        newView.requestLayout();
        return newView;
    }
}
