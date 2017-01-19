package com.tsj.dat153.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.oblig1.R;

import java.util.Collections;
import java.util.Random;

import java.util.List;

import com.tsj.dat153.model.Person;

public class LearningModeActivity extends AppCompatActivity {

    private List<Person> persons;
    private int counter;
    private int score;
    private ViewGroup layout;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        counter = 0;
        score = 0;

        image = (ImageView) findViewById(R.id.image);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        persons = DAO.getPersonList();

        long seed = System.nanoTime();
        Collections.shuffle(persons, new Random(seed));

        layout = (ViewGroup) findViewById(R.id.activity_learning_mode);

        nextPerson();
    }

    public void onGuess(View v){
        TextView counterView = (TextView) findViewById(R.id.counter);
        TextView scoreView = (TextView) findViewById(R.id.score);
        EditText textView = (EditText) findViewById(R.id.guess_name);
        Editable text = textView.getEditableText();
        String name = text.toString();
        Person per = persons.get(counter);

        counter++;
        String count = Integer.toString(counter);
        counterView.setText(count);

        if(per.getName().toLowerCase().equals(name.trim().toLowerCase())){
            score++;
            String sco = Integer.toString(score);
            scoreView.setText(sco);
            scoreView.setTextColor(Color.GREEN);
            counterView.setTextColor(Color.GREEN);
        } else{
            scoreView.setTextColor(Color.RED);
            counterView.setTextColor(Color.RED);
        }
        textView.setText("");
        nextPerson();
    }

    public void nextPerson(){
        if(counter < persons.size()){
            Person pers = persons.get(counter);
            image.setImageBitmap(pers.getImage());
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
