package com.android.paritosh.ngoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Survey(View view) {
    startActivity(new Intent(MainActivity.this,SurveyActivity.class));

    }

    public void analytics(View view) {
    }

    public void features(View view) {
    }
}

//todo 5 create a basic idont know what


// todo 1 create a login screen
// todo 2 create a registration screen
// todo 3 --fill in todo--

//todo create a reg page