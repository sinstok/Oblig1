package com.tsj.dat153.activities;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tsj.dat153.database.DAO;
import com.tsj.dat153.helpers.PersonListViewAdapter;
import com.tsj.dat153.model.Person;
import com.tsj.dat153.oblig1.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;


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

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                /*int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_PERMISSIONS);
                }*/
                takePicture();
                return true;

            case R.id.action_gallery:
                openGallery();

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    takePicture();
                } else {
                    // Permission Denied
                    Toast.makeText(NameListActivity.this, "WRITE_EXTERNAL_STORAGE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void takePicture(){
        //Log.d("HELLO", "rakePicture()");
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
                //Log.d("HELLO", "not missing");
                //Uri photoURI = FileProvider.getUriForFile(this, "com.tsj.dat153.oblig1.fileprovider", photoFile);
                Uri androidURI = Uri.fromFile(photoFile);
                //Log.d("HELLO", androidURI.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, androidURI);
                startActivityForResult(takePictureIntent, 1);
            } else {
                //Log.d("HELLO", "missing");
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Intent intent = new Intent(this, ChooseNameActivity.class);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            scanFile(mCurrentPhotoPath);
            //Log.w("HELLO", mCurrentPhotoPath + " Request code 1");
            intent.putExtra("picture", mCurrentPhotoPath);
            intent.putExtra("whereTo", 1);
            startActivity(intent);
        } else if(requestCode == 2 && resultCode == RESULT_OK){
            Uri selectedImageUri = data.getData();
            String selectedImagePath = getRealPathFromURI(getApplicationContext(), selectedImageUri);
            //Log.e("HELLO", selectedImagePath);
            intent.putExtra("picture", selectedImagePath);
            intent.putExtra("whereTo", 2);
            startActivity(intent);
        }
    }

    public static String getRealPathFromURI(Context context, Uri uri){
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();

        return filePath;
    }

    static String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TSJ_" + timeStamp;
        //File storageDir = getExternalFilesDir("pictures/");
        //File storageDir =  getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //storageDir.mkdirs();
        //Log.d("HELLO", storageDir.getAbsolutePath());

        File storageDir = new File(getExternalStorageDirectory() + "/Pictures/tsj");
        if(!storageDir.exists()){
            storageDir.mkdir();
        }
        File image = new File(getExternalStorageDirectory()+"/Pictures/tsj/" + imageFileName+".jpg");

        //image.createNewFile();

        //File image = File.createTempFile(
        //        imageFileName,  /* prefix */
        //        ".jpg",         /* suffix */
        //        getExternalStorageDirectory()      /* directory */
        //);



        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d("HELLO", image.getAbsolutePath());
        return image;
    }
    public void scanFile(final String filename){
        final File file = new File(filename);
        final Uri fileUri = Uri.fromFile(file);

        final Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(fileUri);
        this.getApplicationContext().sendBroadcast(intent);
    }
}
