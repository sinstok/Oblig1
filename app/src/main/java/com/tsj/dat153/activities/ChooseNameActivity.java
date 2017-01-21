package com.tsj.dat153.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.tsj.dat153.database.DAO;

import com.tsj.dat153.model.Person;

import com.tsj.dat153.oblig1.R;

import java.io.FileDescriptor;
import java.io.IOException;

public class ChooseNameActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_name);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        intent = getIntent();

    }

    public void choosenName(View view) {
        EditText textEdit = (EditText) findViewById(R.id.enter_name);
        Editable editableText = textEdit.getEditableText();
        String name = editableText.toString();

        if (name.trim().equals("")) {
            return;
        }

        //Bitmap imageBitmap = (Bitmap) intent.getParcelableExtra("picture");
        int fromWhere = (int) intent.getIntExtra("whereTo", 0);

        if (fromWhere == 1) {
            String currentPhoto = intent.getStringExtra("picture");
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhoto);
            DAO.addPerson(new Person(name, imageBitmap));
        } else if (fromWhere == 2) {
            String currentPhoto = intent.getStringExtra("picture");
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhoto);
            DAO.addPerson(new Person(name, bitmap));
        }
        Intent intent2 = new Intent(this, NameListActivity.class);
        startActivity(intent2);

    }
}
