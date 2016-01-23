package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    Spinner designerSpin, styleSpin, sizeSpin,vielSpin, cleaningSpin, goToGoogleMaps;
    Button enterYourLocation, searchButton, photoImportButton;
    String designer, ImportPhotos, size, style, viel, cleaning, idReceived, emailReceived;
    Bitmap yourSelectedImage;
    byte[] returnedImage;
    ByteArrayOutputStream stream;

    /// Variable to store address  of users chosen photo from device gallery ///
    private static final int SELECT_PHOTO = 1;

    /// Initiate database ///
    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.dresses);

        designerSpin = (Spinner) findViewById(R.id.designerSpinner);
        styleSpin = (Spinner) findViewById(R.id.styleSpinner);
        sizeSpin = (Spinner) findViewById(R.id.sizeSpinner);
        vielSpin = (Spinner) findViewById(R.id.vielSpinner);
        cleaningSpin = (Spinner) findViewById(R.id.cleaningSpinner);
        enterYourLocation = (Button) findViewById(R.id.locationButton);
        searchButton = (Button) findViewById(R.id.searchBut);
        photoImportButton = (Button) findViewById(R.id.photoImportBut);

        createDatabase(null);
        difficultSpin();
        addListenerDesignSpinner();
        addStyleSpinner();
        addListenerStyleSpinner();
        addSizeSpinner();
        addListenerSizeSpinner();
        addVielSpinner();
        addListenerVielSpinner();
        addDryCleaningSpinner();
        addListenerDryCleaningSpinner();
        inputExamples();

        /// Intent to receive foreign key passed from UserPreferences Class ///
        String idReceived = getIntent().getExtras().getString("idPassed");
        // Toast to check activity received foreign key, will be removed before lauch so user cannot view ///
        Toast.makeText(this, idReceived, Toast.LENGTH_SHORT).show();

        //inputExamples();

    }


    public void createDatabase(View view) {

        // Open the previously created myDB database
        // Pass the database name, designate that only this app can use it

        try {
            myDB = this.openOrCreateDatabase("ProfileDB",
                    MODE_PRIVATE, null);

            // Exectute SQL statement to create the dressDetails table
            myDB.execSQL("CREATE TABLE IF NOT EXISTS dressdetails " +
                    "(dress_id integer primary key AUTOINCREMENT, profile_id INTEGER, image1 BLOB, designer VARCHAR, style VARCHAR, size VARCHAR, viel VARCHAR, " +
                    "drycleaning VARCHAR, FOREIGN KEY(profile_id) REFERENCES profile(id));");

            /* *** JORDI this is the create statement I have been using to include an image ***
            *
            * myDB.execSQL("CREATE TABLE IF NOT EXISTS dressdetails " +
                    "(dress_id integer primary key AUTOINCREMENT, profile_id INTEGER, image1 BLOB, designer VARCHAR, style VARCHAR, size VARCHAR, viel VARCHAR, " +
                    "drycleaning VARCHAR, FOREIGN KEY(profile_id) REFERENCES profile(id));");
            *
            * */
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
                        returnedImage = stream.toByteArray();
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
        myDB.execSQL("INSERT INTO dressdetails(profile_id, designer, style, size, viel, drycleaning) VALUES (" + idReceived + ", '" +
                Designer + "', '" + Style + "', '" + WhatSize + "', '" + WantViel + "', '" + DryCleaningCost + "');");

        /* *** Jordi below is the insert statement I have been trying to use including the image ***
        *
        * myDB.execSQL("INSERT INTO dressdetails(profile_id, designer, style, size, viel, drycleaning) VALUES ('" + idReceived + "', '" +
                yourSelectedImage + "', '" + Designer + "', '" + Style + "', '" + WhatSize + "', '" + WantViel + "', '" + DryCleaningCost + "');");
        *
        * */


        // Intent to move to next activity upon inserting info into database
        Intent moveToNextActivity = new Intent(DressDetails.this, SearchCriteria.class);
        startActivity(moveToNextActivity);

    }

    /// Method to convert the image from int to byte[] so can be stored in database ///
    public byte[] convertToByteArray(int image){

        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(image);
        Bitmap bitmap =  ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] bitmapData = stream.toByteArray();
        return bitmapData;

    }

    /// Sample dresses inputted for your purposes, now when you search there will be some dresses to output /// + yourSelectedImage + "', '"
    public void inputExamples() {

        int image10 = R.drawable.wdone;
        int image2 = R.drawable.wdtwo;
        int image3 = R.drawable.wdthree;
        int image4 = R.drawable.wdfour;
        int image5 = R.drawable.wdfive;
        int image6 = R.drawable.wdsix;
        int image7 = R.drawable.wdseven;
        int image8 = R.drawable.wdeight;
        int image9 = R.drawable.wdnine;

        byte[] eg1 = convertToByteArray(image10);
        byte[] eg2 = convertToByteArray(image2);
        byte[] eg3 = convertToByteArray(image3);
        byte[] eg4 = convertToByteArray(image4);
        byte[] eg5 = convertToByteArray(image5);
        byte[] eg6 = convertToByteArray(image6);
        byte[] eg7 = convertToByteArray(image7);
        byte[] eg8 = convertToByteArray(image8);
        byte[] eg9 = convertToByteArray(image9);


        /// Wanted to import dress details to match the 9 profiles inserted in the profile activity but could not get images to insert into the database from drawable ///
        /// Only managed to get have them imported from gallery, which is the way for a normal user ///

        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (1, '" + eg1 + "', 'Versace', 'Off the shoulder', " +
                "'6', 'Yes', '€25');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (2, '" + eg2 + "','Angel Sanchez', 'Long Sleeved', " +
                "'8', 'No', '€50');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (3, '" + eg3 + "','Carolina Herrera', 'Fairy Tale', " +
                "'10', 'Yes', '€75');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size,   viel, drycleaning) VALUES (4, '" + eg4 + "','Hayley Paige', 'Halter Kneck', " +
                "'12', 'No', '€100');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (5, '" + eg5 + "','Reem Acra', 'Vintage', " +
                "'14', 'Yes', '€75');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (6, '" + eg6 + "','Peter Langner', 'Mid-Length', " +
                "'16', 'No', '€50');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (7, '" + eg7 + "','Pronovias', 'Mermaid', " +
                "'18', 'Yes', '€25');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (8, '" + eg8 + "','Marchesa', 'Ballgown', " +
                "'20', 'No', '€50');");
        myDB.execSQL("INSERT INTO dressdetails(profile_id, image1, designer, style, size, viel, drycleaning) VALUES (9, '" + eg9 + "','Marchesa', 'Trumpet', " +
                "'14', 'Yes', '€75');");
    }

    // Method to query the database and return the primary key for the user, NOT CURRENTLY USED //
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

    public void difficultSpin() {

        designerSpin = (Spinner) findViewById(R.id.designerSpinner);

        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> designSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.DesignerChoice,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        designSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        designerSpin.setAdapter(designSpinnerAdapter);

    }

    public void addListenerDesignSpinner() {

        designerSpin = (Spinner) findViewById(R.id.designerSpinner);

        // Store users choice in a variable enabling us to later add to the profile table
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



    /// Please revert to notes above on first spinner ///
    public void addStyleSpinner(){

        styleSpin = (Spinner) findViewById(R.id.styleSpinner);

        ArrayAdapter<CharSequence> StyleSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Style,
                        android.R.layout.simple_spinner_item);

        StyleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        styleSpin.setAdapter(StyleSpinnerAdapter);

    }

    /// Please revert to notes above on first spinner ///
    public void addListenerStyleSpinner() {

        styleSpin = (Spinner) findViewById(R.id.styleSpinner);


        styleSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            // Store users choice in a variable enabling us to later add to the profile table
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

    /// Please revert to notes above on first spinner ///
    public void addSizeSpinner(){

        sizeSpin = (Spinner) findViewById(R.id.sizeSpinner);

        ArrayAdapter<CharSequence> SizeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Size,
                        android.R.layout.simple_spinner_item);

        SizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sizeSpin.setAdapter(SizeSpinnerAdapter);

    }

    /// Please revert to notes above on first spinner ///
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

    /// Please revert to notes above on first spinner ///
    public void addVielSpinner(){

        vielSpin = (Spinner) findViewById(R.id.vielSpinner);

        ArrayAdapter<CharSequence> vielSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Viel,
                        android.R.layout.simple_spinner_item);

        vielSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vielSpin.setAdapter(vielSpinnerAdapter);

    }

    /// Please revert to notes above on first spinner ///
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

    /// Please revert to notes above on first spinner ///
    public void addDryCleaningSpinner(){

        cleaningSpin = (Spinner) findViewById(R.id.cleaningSpinner);

        ArrayAdapter<CharSequence> cleaningSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.DryCleaningCost,
                        android.R.layout.simple_spinner_item);

        cleaningSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cleaningSpin.setAdapter(cleaningSpinnerAdapter);

    }

    /// Please revert to notes above on first spinner ///
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

    /// On click intent to take user to googlemaps using an intent ///
    public void enterLocation(View view) {

        Intent startGoogleSplash = new Intent(DressDetails.this, GoogleMapsSplash.class);
        startActivity(startGoogleSplash);
        Toast.makeText(DressDetails.this, "Button Clicked", Toast.LENGTH_SHORT).show();


    }


}
