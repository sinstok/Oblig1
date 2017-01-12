package com.tsj.dat153.oblig1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class LearningMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        int counter = 0;
        int score = 0;

        List<Person> liste = MainActivity.liste;

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_learning_mode);
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.lionel_richie);
        layout.addView(image, 0);
    }
}
