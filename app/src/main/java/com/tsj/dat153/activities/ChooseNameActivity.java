package com.tsj.dat153.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

    //Bruker den ikke for øyeblikket, men den er her hvis vi trenger den.
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int oriWidth = bm.getWidth();

        int oriHeight = bm.getHeight();


        float scaleWidth = ((float) newWidth) / oriWidth;

        float scaleHeight = ((float) newHeight) / oriHeight;

        // create a matrix for the manipulation

        Matrix matrix = new Matrix();


        // resize the bit map

        if(oriWidth > oriHeight){
            matrix.postRotate(90);
            matrix.postScale(scaleWidth, scaleHeight);
            matrix.postRotate(-90);
        } else {
            matrix.postScale(scaleWidth, scaleHeight);
        }


        // recreate the new Bitmap

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, oriWidth, oriHeight, matrix, false);



        return resizedBitmap;

    }
}
