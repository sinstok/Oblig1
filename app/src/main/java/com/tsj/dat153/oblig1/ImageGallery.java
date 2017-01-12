package com.tsj.dat153.oblig1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class ImageGallery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 50);

        List<Person> list = MainActivity.liste;
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_image_gallery);

        for(int i = 0; i < list.size(); i++){
            ImageView im = new ImageView(this);
            im.setImageResource(list.get(i).getImage());
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
        Intent intent = new Intent(this, ShowName.class);
        intent.putExtra("name", p.getName());
        startActivity(intent);
    }
}
