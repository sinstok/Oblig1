package com.tsj.dat153.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.oblig1.R;

import com.tsj.dat153.model.Person;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if(DAO.getPersonList().size() == 0) {
            Person p1 = new Person("Jerry", BitmapFactory.decodeResource(this.getResources(), R.drawable.seinfeld_jerry));
            Person p2 = new Person("George", BitmapFactory.decodeResource(this.getResources(), R.drawable.george_costanza));
            Person p3 = new Person("Kramer", BitmapFactory.decodeResource(this.getResources(), R.drawable.kramer));
            Person p4 = new Person("Lionel", BitmapFactory.decodeResource(this.getResources(), R.drawable.lionel_richie));
            DAO.addPerson(p1);
            DAO.addPerson(p2);
            DAO.addPerson(p3);
            DAO.addPerson(p4);
        }
    }

    @Override
    protected  void onStart(){
        super.onStart();
    }

    public void toNameList(View view){
        Intent intent = new Intent(this, NameListActivity.class);
        startActivity(intent);
    }

    public void toImageGallery(View view){
        Intent intent = new Intent(this, ImageGalleryActivity.class);
        startActivity(intent);
    }

    public void toLearningMode(View view){
        Intent intent = new Intent(this, LearningModeActivity.class);
        startActivity(intent);
    }
}
