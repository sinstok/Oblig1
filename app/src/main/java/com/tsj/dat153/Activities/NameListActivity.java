package com.tsj.dat153.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.helpers.PersonListViewAdapter;
import com.tsj.dat153.model.Person;
import com.tsj.dat153.oblig1.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NameListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        ArrayList<Person> persons = (ArrayList<Person>) DAO.getPersonList();

        PersonListViewAdapter adapter = new PersonListViewAdapter(this, persons);

        ListView listView = (ListView) findViewById(R.id.text_list);
        listView.setAdapter(adapter);

        //ViewGroup layout = (ViewGroup) findViewById(R.id.activity_name_list);

        /*for(int i = 0; i < persons.size(); i++){
            Button button = new Button(this);
            button.setText(persons.get(i).getName());
            final Person p = persons.get(i);
            button.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    viewImage(p);
                }
            });
            layout.addView(button);
        }*/

        final Intent intent = new Intent(this, ShowPictureActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person item = (Person) parent.getItemAtPosition(position);
                intent.putExtra("name", item.getName());
                startActivity(intent);
            }
        });
    }

    public void viewImage(Person p){
        Intent intent = new Intent(this, ShowPictureActivity.class);
        intent.putExtra("name", p.getName());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.phot_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                takePicture();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void takePicture(){
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
            intent.putExtra("picture", mCurrentPhotoPath);
            startActivity(intent);
        }
    }

    static String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir("pictures/");
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
