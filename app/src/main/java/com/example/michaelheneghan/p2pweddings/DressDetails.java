package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by michaelheneghan on 14/01/2016.
 */
public class DressDetails extends Activity {

    Spinner designerSpin, styleSpin, sizeSpin,vielSpin, cleaningSpin, goToGoogleMaps;
    Button enterYourLocation, searchButton, photoImportButton;
    String designer, ImportPhotos, size, style, viel, cleaning, idReceived, emailReceived;
    Bitmap yourSelectedImage;
    private static final int SELECT_PHOTO = 1;

    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dresses);

        //designerSpin = (Spinner) findViewById(R.id.designSpinner);
        styleSpin = (Spinner) findViewById(R.id.styleSpinner);
        sizeSpin = (Spinner) findViewById(R.id.sizeSpinner);
        vielSpin = (Spinner) findViewById(R.id.vielSpinner);
        cleaningSpin = (Spinner) findViewById(R.id.cleaningSpinner);
        enterYourLocation = (Button) findViewById(R.id.locationButton);
        searchButton = (Button) findViewById(R.id.searchBut);
        photoImportButton = (Button) findViewById(R.id.photoImportBut);

        createDatabase(null);
        //addDesigner();
        //addListenerDesignSpinner();
        addStyleSpinner();
        addListenerStyleSpinner();
        addSizeSpinner();
        addListenerSizeSpinner();
        addVielSpinner();
        addListenerVielSpinner();
        addDryCleaningSpinner();
        addListenerDryCleaningSpinner();

    }


    public void createDatabase(View view) {

        // Open the previously created myDB database
        // Pass the database name, designate that only this app can use it

        try {
            myDB = this.openOrCreateDatabase("ProfileDB",
                    MODE_PRIVATE, null);

            // Exectute SQL statement to create the dressDetails table
            myDB.execSQL("CREATE TABLE IF NOT EXISTS dressdetails " +
                    "(dress_id integer primary key AUTOINCREMENT, profile_id INTEGER, image BLOB, designer VARCHAR, style VARCHAR, size VARCHAR, viel VARCHAR, " +
                    "drycleaning VARCHAR, FOREIGN KEY(profile_id) REFERENCES profile(id));");

            // Input database address into variable to check database has in fact been created
            File database = getApplicationContext().getDatabasePath("ProfileDB.db");

            // Check if the database exists
            if (!database.exists()) {
                Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Database Missing", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

            Log.e("PROFILE ERROR", "Error Creating Database");

        }
    }

    // on click intent to take user into their devices image library to choose a picture to display
    public void importPhotoFromGallery(View view) {

        photoImportButton = (Button) findViewById(R.id.photoImportBut);

        photoImportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
    }

    // Method to receive bundle with image Uri and convert to bitmap so it can be inputted into the database
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        try{
            switch(requestCode) {
                case SELECT_PHOTO:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = imageReturnedIntent.getData();
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                        stream.toByteArray();
                    }
            }
            // Catch input/output errors
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void InsertIntoTheDatabase(View view) {

        // Get the the value of the chosen option in each spinner
        String Designer = designer;
        String Style = style;
        String WhatSize = size;
        String WantViel = viel;
        String DryCleaningCost = cleaning;

        // Execute SQL statement to insert new data
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image, designer, style, size, viel, drycleaning) VALUES ('" + idReceived + "', '" + yourSelectedImage + "', '" +
                Designer + "', '" + Style + "', '" + WhatSize + "', '" + WantViel + "', '" + DryCleaningCost + "');");

        // Intent to move to next activity upon inserting info into database
        Intent moveToNextActivity = new Intent(DressDetails.this, SearchCriteria.class);
        startActivity(moveToNextActivity);

    }

    public void inputExamples(){

        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (1, 'Off the shoulder', " +
                "6, 'Yes', '€25');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (2, 'Long Sleeved', " +
                "8, 'No', '€50');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (3, 'Fairy Tale', " +
                "10, 'Yes', '€75');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (4, 'Halter Kneck', " +
                "12, 'No', '€100');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (5', 'Vintage', " +
                "14, 'Yes', '€75');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (6, 'Mid-Length', " +
                "16, 'No', '€50');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (7', 'Mermaid', " +
                "18, 'Yes', '€25');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES ('8', 'Ballgown', " +
                "20, 'No', '€50');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, style, size, viel, drycleaning) VALUES (9', 'Trumpet', " +
                "14, 'Yes', '€75');");
    }

    private String getProfileID(String idReceived){
        String selectQuery = "SELECT size, style FROM dressdetails WHERE profile_id = ?";
        Cursor c = myDB.rawQuery(selectQuery, new String[] { idReceived });
        String tempStyle = "";
        String two = "";
        if (c.moveToFirst()) {
            tempStyle += c.getString(0);
            two += c.getString(1);
        }
        c.close();
        return tempStyle + " : " + two;
    }


/*

    public void addDesigner() {

        designerSpin = (Spinner) findViewById(R.id.designSpinner);

        ArrayAdapter<CharSequence> designSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.DesignerChoice,
                        android.R.layout.simple_spinner_item);

        designSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        designerSpin.setAdapter(designSpinnerAdapter);

    }


    public void addListenerDesignSpinner() {

        designerSpin = (Spinner) findViewById(R.id.designSpinner);


        designerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                designer = itemSelectedInSpinner;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
*/
    // Insert back into activity once button and ET has been created in xml //
    public void addStyleSpinner(){

        styleSpin = (Spinner) findViewById(R.id.styleSpinner);

        ArrayAdapter<CharSequence> StyleSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Style,
                        android.R.layout.simple_spinner_item);

        StyleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        styleSpin.setAdapter(StyleSpinnerAdapter);

    }

    public void addListenerStyleSpinner() {

        styleSpin = (Spinner) findViewById(R.id.styleSpinner);


        styleSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                style = itemSelectedInSpinner;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void addSizeSpinner(){

        sizeSpin = (Spinner) findViewById(R.id.sizeSpinner);

        ArrayAdapter<CharSequence> SizeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Size,
                        android.R.layout.simple_spinner_item);

        SizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sizeSpin.setAdapter(SizeSpinnerAdapter);

    }

    public void addListenerSizeSpinner(){

        sizeSpin = (Spinner) findViewById(R.id.sizeSpinner);


        sizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                size = itemSelectedInSpinner;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void addVielSpinner(){

        vielSpin = (Spinner) findViewById(R.id.vielSpinner);

        ArrayAdapter<CharSequence> vielSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Viel,
                        android.R.layout.simple_spinner_item);

        vielSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vielSpin.setAdapter(vielSpinnerAdapter);

    }

    public void addListenerVielSpinner(){

        vielSpin = (Spinner) findViewById(R.id.vielSpinner);


        vielSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                viel = itemSelectedInSpinner;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void addDryCleaningSpinner(){

        cleaningSpin = (Spinner) findViewById(R.id.cleaningSpinner);

        ArrayAdapter<CharSequence> cleaningSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.DryCleaningCost,
                        android.R.layout.simple_spinner_item);

        cleaningSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cleaningSpin.setAdapter(cleaningSpinnerAdapter);

    }

    public void addListenerDryCleaningSpinner(){

        cleaningSpin = (Spinner) findViewById(R.id.cleaningSpinner);


        sizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                cleaning = itemSelectedInSpinner;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // on click intent to take user to googlemaps using an intent
    public void enterLocation(View view) {

        Intent startGoogleSplash = new Intent(DressDetails.this, GoogleMapsSplash.class);
        startActivity(startGoogleSplash);
        Toast.makeText(DressDetails.this, "Button Clicked", Toast.LENGTH_SHORT).show();


    }

}
