package com.tsj.dat153.oblig1;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;

import java.util.List;


public class NameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);

        List<Person> list = MainActivity.liste;
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
        Intent intent = new Intent(this, ShowPicture.class);
        intent.putExtra("picture", p.getImage());
        startActivity(intent);
    }
}
