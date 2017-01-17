package com.tsj.dat153.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.oblig1.R;

import com.tsj.dat153.model.Person;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(DAO.getPersonList().size() == 0) {
            Person p1 = new Person("Jerry", R.drawable.seinfeld_jerry);
            Person p2 = new Person("George", R.drawable.george_costanza);
            Person p3 = new Person("Kramer", R.drawable.kramer);
            Person p4 = new Person("Lionel", R.drawable.lionel_richie);
            DAO.addPerson(p1);
            DAO.addPerson(p2);
            DAO.addPerson(p3);
            DAO.addPerson(p4);
        }
    }

    @Override
    protected  void onStart(){
        super.onStart();
    }

    public void toNameList(View view){
        Intent intent = new Intent(this, NameListActivity.class);
        startActivity(intent);
    }

    public void toImageGallery(View view){
        Intent intent = new Intent(this, ImageGalleryActivity.class);
        startActivity(intent);
    }

    public void toLearningMode(View view){
        Intent intent = new Intent(this, LearningModeActivity.class);
        startActivity(intent);
    }
}
