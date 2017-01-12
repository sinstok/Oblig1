package com.tsj.dat153.oblig1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class NameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);
    }

    @Override
    protected void onStart(){
        super.onStart();
        PersonContainer cont = (PersonContainer) getApplicationContext();
        List<Person> liste = cont.getPersonList();
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_name_list);
        /*Button b1 = new Button(this);
        b1.setText();
        Button*/
        for(int i = 0; i < liste.size(); i++){
            Button button = new Button(this);
            button.setText(liste.get(i).getName());
            layout.addView(button);
        }

    }

    public void viewImage(View view){

        /*ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(p1.getImage());*/
        /*TextView textView = new TextView(this);
        textView.setText(p1.getName());
        layout.addView(textView);*/
    }
}
