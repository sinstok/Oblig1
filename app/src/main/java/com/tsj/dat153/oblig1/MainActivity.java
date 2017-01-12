package com.tsj.dat153.oblig1;

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

    public void toNameList(View view){
        Intent intent = new Intent(this, NameList.class);
        startActivity(intent);
    }

    public void toImageGallery(View view){
        Intent intent = new Intent(this, ImageGallery.class);
        startActivity(intent);
    }

    public void toLearningMode(View view){
        Intent intent = new Intent(this, LearningMode.class);
        startActivity(intent);
    }
}
