package com.android.paritosh.ngoapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SurveyActivity extends AppCompatActivity {
    private SurveyAdapter surveyAdapter;
    private QuestionsDataModel questionsDataModel;
    private ArrayList<QuestionsDataModel> listOfQuestions,tempList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private String[] answers;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        recyclerView = findViewById(R.id.rv_qna);
        listOfQuestions = new ArrayList<>();
        tempList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_qna);

        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        surveyAdapter = new SurveyAdapter(listOfQuestions,getApplicationContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(surveyAdapter);
        answers = new String[5];
        listAdder();
    }

    private void listAdder(){
        //add the data String q,String[] op @params
        SurveyAdapter.answerDB.initialiseAnsArray(5);
        String qu[] = new String[]{"What is your name?","What is your gender?","What is your age?",
                "What is your occupation?","Total no. of voters in family?","What benefits did you recieve from the government?","","","",""};
        String[] opu = new String[]{"good","bad","real bad","feeling clumsy"};
        //format the data into data model and add it into a temp list
        int i,j[];
        j = new int[]{1,0,1,0,0};
        for(i=0;i<5;i++) {
            questionsDataModel = new QuestionsDataModel(qu[i], opu, 0);
            listOfQuestions.add(questionsDataModel);
            surveyAdapter.notifyDataSetChanged();
            //updateDataAsync(questionsDataModel);
        }

    }
// if you get problems while using recycler views then use the thread below to sort out the problem
    //log 1: problem occured via using ListView:: sol:: switced to recycler view!
//    void updateDataAsync(QuestionsDataModel q)
//    {
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                // Make updates the "data" list.
//                //tempList.add(questionsDataModel);
//
//                // Update your adapter.
//                refreshList();
//            }
//        }).start();
//    }
//
//    void refreshList()
//    {
//        runOnUiThread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
////                listOfQuestions.clear();
////                listOfQuestions.addAll(tempList);\
//                listOfQuestions.add(questionsDataModel);
//                surveyAdapter.notifyDataSetChanged();
//            }
//        });
//    }

    public void submitAns(View view) {
//        String s = answerFromRV();
//        Toast.makeText(SurveyActivity.this, s+"", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, Arrays.toString(SurveyAdapter.answerDB.getArray())+"", Toast.LENGTH_SHORT).show();


    }
}
