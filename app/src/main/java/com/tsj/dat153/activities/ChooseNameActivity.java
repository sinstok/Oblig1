package com.tsj.dat153.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.tsj.dat153.database.DAO;

import com.tsj.dat153.model.Person;

import com.tsj.dat153.oblig1.R;

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

    public void choosenName(View view){
        EditText textEdit = (EditText) findViewById(R.id.enter_name);
        Editable editableText = textEdit.getEditableText();
        String name = editableText.toString();

        //Bitmap imageBitmap = (Bitmap) intent.getParcelableExtra("picture");
        String currentPhoto = intent.getStringExtra("picture");
        Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhoto);

        DAO.addPerson(new Person(name, imageBitmap));

        Intent intent2 = new Intent(this, NameListActivity.class);
        startActivity(intent2);

    }
}
