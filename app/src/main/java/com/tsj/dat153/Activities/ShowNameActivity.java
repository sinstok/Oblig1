package com.tsj.dat153.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsj.dat153.oblig1.R;

public class ShowNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        TextView view = new TextView(this);
        view.setText(name);
        view.setTextSize(70);
        layout.addView(view);
    }
}
