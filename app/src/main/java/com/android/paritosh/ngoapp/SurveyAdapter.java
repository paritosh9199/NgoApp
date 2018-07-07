package com.android.paritosh.ngoapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Paritosh on 6/1/2018.
 */

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.MyViewHolder> {
    private ArrayList<QuestionsDataModel> myValues;
    private TextView question;
    private QuestionsDataModel dataModel;
    private String answer;
    public List<String> answerList;
    private static RadioGroup lastCheckedRadioGroup = null;
    private static Context context;
    private static int cbox;


    public SurveyAdapter (ArrayList<QuestionsDataModel> myValues,Context context){
        this.myValues = myValues;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.srv_question_obj, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(myValues.get(position).getQuestion()!="") {
            holder.myTextView.setText(myValues.get(position).getQuestion());
            Log.i("position",myValues.get(position).getQuestion()+" and the position is: "+position);
        }
        int type = myValues.get(position).getType();
        // @var type defines the type of question that is being displayed
        if(type == 0){
            //single type
            int i;
            for(i=0;i<6;i++){
                holder.cb[i].setVisibility(View.GONE);
            }
            optionSetter(holder,position,type);
            String[] opt = myValues.get(position).getOptions();
            int noOfVisibleOp = opt.length;
            Log.i("len",opt.length+"");
            //utils = new RadioUtils();
            //utils.setRadioExclusiveClick(holder.rg,position);
            for(i=0;i<opt.length;i++){
                Log.i("opt",opt[i]);
                holder.rb[i].setText(opt[i]);
            }
        }
        if(type == 1){
            //multiple type
            int i;
            holder.rg.setVisibility(View.GONE);
//            for(i=0;i<6;i++){
//                holder.rb[i].setVisibility(View.GONE);
//            }
            optionSetter(holder,position,type);
            String[] opt = myValues.get(position).getOptions();
            int noOfVisibleOp = opt.length;
            Log.i("len",opt.length+"");
            for(i=0;i<opt.length;i++){
                Log.i("opt",opt[i]);
                holder.cb[i].setText(opt[i]);
            }
        }
        if(type == 2){
            //fill the answer type--will work on this later if asked for!

//            for(i=0;i<6;i++){
//                holder.op[i].setVisibility(View.GONE);
//            }

            optionSetter(holder,position,type);
        }

        QuestionsDataModel q = myValues.get(position);
        Log.i("Questions:",q.getQuestion()+q.getType()+q.getOptions());

    }


    //defined for setting the visibility of unused views in the recycler views!
    public void optionSetter(MyViewHolder holder, int position,int type){
        String[] op = myValues.get(position).getOptions();
        int noOfVisibleOp = op.length;
        int totalNoOfOp = 6;
        int d = totalNoOfOp - noOfVisibleOp,i;
        if(totalNoOfOp>=noOfVisibleOp) {
            if(type == 0) {
                for (i = totalNoOfOp - 1; i > noOfVisibleOp - 1; i--) {
                    holder.rb[i].setVisibility(View.GONE);
                }
            } else if (type == 1) {
                for (i = totalNoOfOp - 1; i > noOfVisibleOp - 1; i--) {
                    holder.cb[i].setVisibility(View.GONE);
                }
            }
        }

    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
        private CheckBox cb[] = new CheckBox[6];
        private RadioButton rb[] = new RadioButton[6];
        private RadioGroup rg;
        private LinearLayout linearLayout;
        //private
        public MyViewHolder(final View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ll_checkbox);
            myTextView = itemView.findViewById(R.id.tv_question);
            rg = itemView.findViewById(R.id.rg_options);
            cb[0] = itemView.findViewById(R.id.cb1);
            cb[1] = itemView.findViewById(R.id.cb2);
            cb[2] = itemView.findViewById(R.id.cb3);
            cb[3] = itemView.findViewById(R.id.cb4);
            cb[4] = itemView.findViewById(R.id.cb5);
            cb[5] = itemView.findViewById(R.id.cb6);
            rb[0] = itemView.findViewById(R.id.rb1);
            rb[1] = itemView.findViewById(R.id.rb2);
            rb[2] = itemView.findViewById(R.id.rb3);
            rb[3] = itemView.findViewById(R.id.rb4);
            rb[4] = itemView.findViewById(R.id.rb5);
            rb[5] = itemView.findViewById(R.id.rb6);
            //todo initialise views here--done

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    int id = rg.getCheckedRadioButtonId();
                    int pos = getAdapterPosition()+1;
                    RadioButton r = itemView.findViewById(id);
                    Log.i("text from rb",r.getText().toString()+" at position: "+pos);
                    SurveyAdapter.answerDB.setElement(pos-1,r.getText().toString());
                    //Toast.makeText(SurveyAdapter.context, "clicked: "+r.getText().toString()+" at position: "+pos, Toast.LENGTH_SHORT).show();
                }
            });

            //buggy code Dont use!
//            for(cbox=0;cbox<6;cbox++){
//                cb[cbox].setOnClickListener(new CheckBox.OnClickListener(){
//                    @Override
//                    public void onClick(View view) {
//
//                        int pos = getAdapterPosition()+1;
//                        Log.i("cb text",cb[cbox].getText().toString()+" at position: "+pos);
//                        Toast.makeText(SurveyAdapter.context, "clicked: "+cb[cbox].getText().toString()+" at position: "+pos, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }



        }
    }

    public static class answerDB{
        public static String[] ans;
        public static int s;
        public static void initialiseAnsArray(int size){
            s = size;
            ans = new String[s];
        }

        public static String[] getArray(){
            return ans;
        }

        public static void setElement(int pos,String element){
            //set element at position
            ans[pos] = element;
        }
    }
}
