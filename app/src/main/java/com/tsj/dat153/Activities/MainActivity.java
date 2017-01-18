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

    public void takePicture(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.tsj.dat153.oblig1.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(this, ChooseNameActivity.class);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bitmap map = BitmapFactory.decodeFile(mCurrentPhotoPath);
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //intent.putExtra("picture", (Bitmap) extras.get("data"));
            intent.putExtra("picture", mCurrentPhotoPath);
            startActivity(intent);
        }
    }

    static String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
