package com.android.paritosh.ngoapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    private SurveyAdapter surveyAdapter;
    private QuestionsDataModel questionsDataModel,tempDm;
    private ArrayList<QuestionsDataModel> listOfQuestions,tempList;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;

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
        surveyAdapter = new SurveyAdapter(listOfQuestions);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(surveyAdapter);
        listAdder();
    }

    private void listAdder(){
        //add the data String q,String[] op @params
        String qu[] = new String[]{"how are you doing today?","how are you doing today?","how are you doing today?","how are you doing today?","how are you doing today?"};
        String[] opu = new String[]{"good","bad","real bad","feeling clumsy"};
        //format the data into data model and add it into a temp list
        int i,j[];
        j = new int[]{1,0,1,0,0};
        for(i=0;i<5;i++) {
            questionsDataModel = new QuestionsDataModel(qu[i], opu, 1);
            updateDataAsync(questionsDataModel);
        }

    }

    void updateDataAsync(QuestionsDataModel q)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Make updates the "data" list.
                tempList.add(questionsDataModel);

                // Update your adapter.
                refreshList();
            }
        }).start();
    }

    void refreshList()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                listOfQuestions.clear();
                listOfQuestions.addAll(tempList);
                surveyAdapter.notifyDataSetChanged();
            }
        });
    }



}
