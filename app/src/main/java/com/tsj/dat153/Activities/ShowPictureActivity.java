package com.tsj.dat153.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.model.Person;
import com.tsj.dat153.oblig1.R;

import java.util.List;

public class ShowPictureActivity extends AppCompatActivity {
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        personList = DAO.getPersonList();

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_picture);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Bitmap image = DAO.getBitmap(name);
        ImageView view = new ImageView(this);
        layout.addView(view);
        if (image == null) {
            view.setImageResource(R.drawable.lionel_richie);
        } else {
            view.setImageBitmap(image);
        }
    }

}
