package com.tsj.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.oblig1.R;

import java.util.List;

import com.tsj.dat153.model.Person;


public class ImageGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 50);

        List<Person> list = DAO.getPersonList();
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_image_gallery);

        for(int i = 0; i < list.size(); i++){
            ImageView im = new ImageView(this);
            im.setImageBitmap(list.get(i).getImage());
            im.setLayoutParams(params);
            layout.addView(im);
            final Person p = list.get(i);
            im.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    viewName(p);
                }
            });
        }
    }

    public void viewName(Person p){
        Intent intent = new Intent(this, ShowNameActivity.class);
        intent.putExtra("name", p.getName());
        startActivity(intent);
    }
}
