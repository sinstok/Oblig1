package com.tsj.dat153.oblig1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected  void onStart(){
        super.onStart();
        PersonContainer container = (PersonContainer) getApplicationContext();
        Person p1 = new Person("Jerry", R.drawable.seinfeld_jerry);
        Person p2 = new Person("George", R.drawable.george_costanza);
        Person p3 = new Person("Kramer", R.drawable.kramer);
        container.addPerson(p1);
        container.addPerson(p2);
        container.addPerson(p3);
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
