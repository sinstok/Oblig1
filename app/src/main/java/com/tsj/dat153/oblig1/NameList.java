package com.tsj.dat153.oblig1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;


public class NameList extends AppCompatActivity {
    private Person p1;
    private Person p2;
    private Person p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);


    }

    public void viewImage(View view){
        p1 = new Person("Sindre", R.drawable.seinfeld_jerry);
        //p2 = new Person("Joakim", "android.resources://" + "com.tsj.dat153.oblig1/drawable/George-costanze.jpg");
        //p3 = new Person("Tomas", "android.resources://" + "com.tsj.dat153.oblig1/drawable/Kramer.jpg");
        ImageView layout = (ImageView) findViewById(R.id.image);
        layout.setImageResource(p1.getImage());
    }
}
