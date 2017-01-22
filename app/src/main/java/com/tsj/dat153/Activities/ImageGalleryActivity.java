package com.tsj.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.helpers.ImageGridViewAdapter;
import com.tsj.dat153.oblig1.R;

import java.util.ArrayList;
import java.util.List;

import com.tsj.dat153.model.Person;


public class ImageGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);


        ArrayList<Person> persons = (ArrayList<Person>) DAO.getPersonList();
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        final ImageGridViewAdapter adapter = new ImageGridViewAdapter(this, persons);
        gridView.setAdapter(adapter);

        final Intent intent = new Intent(this, ShowNameActivity.class);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person p = (Person) gridView.getItemAtPosition(position);
                intent.putExtra("name", p.getName());
                startActivity(intent);
            }
        });
    }

    //Brukes ikke.
    public void viewName(Person p){
        Intent intent = new Intent(this, ShowNameActivity.class);
        intent.putExtra("name", p.getName());
        startActivity(intent);
    }
}
