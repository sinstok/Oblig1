package com.tsj.dat153.activities;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.oblig1.R;

import java.util.List;

import com.tsj.dat153.model.Person;


public class NameListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        List<Person> list = DAO.getPersonList();
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_name_list);

        for(int i = 0; i < list.size(); i++){
            Button button = new Button(this);
            button.setText(list.get(i).getName());
            final Person p = list.get(i);
            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    viewImage(p);
                }
            });
            layout.addView(button);
        }
    }

    public void viewImage(Person p){
        Intent intent = new Intent(this, ShowPictureActivity.class);
        intent.putExtra("name", p.getName());
        startActivity(intent);
    }
}
