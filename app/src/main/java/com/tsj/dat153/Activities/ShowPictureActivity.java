package com.tsj.dat153.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tsj.dat153.oblig1.R;

public class ShowPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_picture);

        Intent intent = getIntent();
        int image = intent.getIntExtra("picture", 0);
        ImageView view = new ImageView(this);
        layout.addView(view);
        if (image == 0) {
            view.setImageResource(R.drawable.lionel_richie);
        } else {
            view.setImageResource(image);
        }
    }
}