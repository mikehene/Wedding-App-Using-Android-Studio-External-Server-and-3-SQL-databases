package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by michaelheneghan on 14/01/2016.
 */
public class DressDetails extends Activity {

    Spinner designerSpin, styleSpin, sizeSpin,vielSpin, cleaningSpin;
    Button searchButton;
    String designer, ImportPhotos, size, style, viel, cleaning, idReceived;

    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dresses);

        designerSpin = (Spinner) findViewById(R.id.designSpinner);
        styleSpin = (Spinner) findViewById(R.id.styleSpinner);
        sizeSpin = (Spinner) findViewById(R.id.sizeSpinner);
        vielSpin = (Spinner) findViewById(R.id.vielSpinner);
        cleaningSpin = (Spinner) findViewById(R.id.cleaningSpinner);

        createDatabase(null);
        addDesigner();
        addListenerDesignSpinner();
        addStyleSpinner();
        addListenerStyleSpinner();

        addVielSpinner();
        addListenerVielSpinner();
        addSizeSpinner();
        addListenerSizeSpinner();
        addDryCleaningSpinner();
        addListenerDryCleaningSpinner();

        Intent recievedIntent = getIntent();
        Bundle bundle = recievedIntent.getExtras();
        idReceived = bundle.getString("idPassed");
        Toast.makeText(this, idReceived, Toast.LENGTH_SHORT).show();
    }


    public void createDatabase(View view) {

        // Open the previously created myDB database
        // Pass the database name, designate that only this app can use it

        try {
            myDB = this.openOrCreateDatabase("ProfileDB",
                    MODE_PRIVATE, null);

            // Exectute SQL statement to create the dressDetails table
            myDB.execSQL("CREATE TABLE IF NOT EXISTS dressdetails " +
                    "(dress_id integer primary key AUTOINCREMENT, profile_id INTEGER, designer VARCHAR, style VARCHAR, size VARCHAR, viel VARCHAR, " +
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

    public void InsertIntoTheDatabase(View view) {

        // Get the the value of the chosen option in each spinner
        String Designer = designer;
        String Style = style;
        String WhatSize = size;
        String WantViel = viel;
        String DryCleaningCost = cleaning;

        // Execute SQL statement to insert new data
        myDB.execSQL("INSERT INTO dressdetails(profile_id, designer, style, size, viel, drycleaning) VALUES ('" + idReceived + "', '" +
                Designer + "', '" + Style + "', '" + WhatSize + "', '" + WantViel + "', '" + DryCleaningCost + "');");

        String result = getProfileID(idReceived);

        if(result != null){
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }


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
                        R.array.RentBuy,
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





/*
    public void gotogooglemaps(View view) {

        Intent myIntent = new Intent(DressDetails.this,GoogleMapsSplash.class);
        //myIntent.putExtra("idPassed",getEmail(userEmail));
        startActivity(myIntent);

    }*/
}
