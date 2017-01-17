package com.tsj.dat153.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
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

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_picture);

        Intent intent = getIntent();
        Bitmap image = (Bitmap) intent.getParcelableExtra("picture");
        ImageView view = new ImageView(this);
        layout.addView(view);
        if (image == null) {
            view.setImageResource(R.drawable.lionel_richie);
        } else {
            view.setImageBitmap(image);
        }
    }
}
