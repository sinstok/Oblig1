package com.tsj.dat153.oblig1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView view = new TextView(this);
        view.setText(name);
        view.setTextSize(70);
        layout.addView(view);
    }
}
