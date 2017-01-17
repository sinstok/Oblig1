package com.tsj.dat153.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private int randNumb;
    private List<Person> liste;
    private int counter;
    private int score;
    private Random number;
    private ViewGroup layout;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);
        counter = 0;
        score = 0;

        image = (ImageView) findViewById(R.id.image);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        liste = DAO.getPersonList();

        number = new Random();
        //randNumb = number.nextInt(liste.size() - 1);

        long seed = System.nanoTime();
        Collections.shuffle(liste, new Random(seed));

        layout = (ViewGroup) findViewById(R.id.activity_learning_mode);

        //Person pers = liste.get(counter);

        nextPerson();

        /*ViewGroup layout = (ViewGroup) findViewById(R.id.activity_learning_mode);
        ImageView image = new ImageView(this);
        image.setImageResource(pers.getImage());
        image.setId(randNumb);
        layout.addView(image, 0);*/
    }

    public void onGuess(View v){
        TextView counterView = (TextView) findViewById(R.id.counter);
        TextView scoreView = (TextView) findViewById(R.id.score);
        EditText textView = (EditText) findViewById(R.id.guess_name);
        Editable text = textView.getEditableText();
        String name = text.toString();
        Person per = liste.get(counter);

        counter++;
        String count = Integer.toString(counter);
        counterView.setText(count);

        if(per.getName().equals(name)){
            score++;
            String sco = Integer.toString(score);
            scoreView.setText(sco);
        }
        textView.setText("");
        nextPerson();
    }

    public void nextPerson(){
        if(counter < liste.size()){
            Person pers = liste.get(counter);
            image.setImageResource(pers.getImage());
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}
