package com.tsj.dat153.oblig1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ShowPicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_picture);

        Intent intent = getIntent();
        int message = intent.getIntExtra("picture", 0);
        ImageView view = new ImageView(this);
        layout.addView(view);
        if (message == 0) {
            view.setImageResource(R.drawable.lionel_richie);
        } else {
            view.setImageResource(message);
        }
    }
}
