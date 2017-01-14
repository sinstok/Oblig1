package com.tsj.dat153.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.oblig1.R;

import java.util.List;

import com.tsj.dat153.model.Person;

public class LearningModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        int counter = 0;
        int score = 0;

        List<Person> liste = DAO.getPersonList();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_learning_mode);
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.lionel_richie);
        layout.addView(image, 0);
    }
}
