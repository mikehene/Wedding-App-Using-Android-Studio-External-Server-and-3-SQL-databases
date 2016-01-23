package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by michaelheneghan on 23/11/2015.
 */
public class SearchCriteria extends Activity {

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    Spinner designerSpin, styleSpinner, sizeSpinner, vielSpin;
    String designerChoice, style, size, viel;
    String queryImageRetrieved, sellerContactDetails, dryCleaningDetails, dressesProfileId;

    /// Initiate database ///
    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.searchdatabase);
        myDB = this.openOrCreateDatabase("ProfileDB", MODE_PRIVATE, null);


        designerSpin = (Spinner) findViewById(R.id.designer_Spin);
        styleSpinner = (Spinner) findViewById(R.id.styleSpin);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpin);
        vielSpin = (Spinner) findViewById(R.id.vielSpinner);

        addSearchDesignerSpinner();
        addListenerDesignerSpinner();
        addStyleSpinner();
        addListenerStyleSpinner();
        addSizeSpinner();
        addListenerSizeSpinner();
        addSearchVielSpinner();
        addListenerVielSpinner();

    }

    public void addSearchDesignerSpinner() {

        designerSpin = (Spinner) findViewById(R.id.designer_Spin);
        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> SearchDesignerSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.DesignerChoice,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        SearchDesignerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        designerSpin.setAdapter(SearchDesignerSpinnerAdapter);

    }

    public void addListenerDesignerSpinner(){

        designerSpin = (Spinner) findViewById(R.id.designer_Spin);

        // Store users choice in a variable enabling us to later add to the dressDetails table
        designerSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                designerChoice = itemSelectedInSpinner;
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void addStyleSpinner() {

        styleSpinner = (Spinner) findViewById(R.id.styleSpin);
        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> styleSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Style,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        styleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        styleSpinner.setAdapter(styleSpinnerAdapter);

    }

    public void addListenerStyleSpinner(){

        styleSpinner = (Spinner) findViewById(R.id.styleSpin);

        // Store users choice in a variable enabling us to later add to the dressDetails table
        styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    public void addSizeSpinner() {

        sizeSpinner = (Spinner) findViewById(R.id.sizeSpin);
        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> sizeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Size,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        sizeSpinner.setAdapter(sizeSpinnerAdapter);

    }

    public void addListenerSizeSpinner(){

        sizeSpinner = (Spinner) findViewById(R.id.sizeSpin);

        // Store users choice in a variable enabling us to later add to the dressDetails table
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


    public void addSearchVielSpinner() {

        vielSpin = (Spinner) findViewById(R.id.vielSpinner);
        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> vielSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.Viel,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        vielSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        vielSpin.setAdapter(vielSpinnerAdapter);

    }

    public void addListenerVielSpinner(){

        vielSpin = (Spinner) findViewById(R.id.vielSpinner);

        // Store users choice in a variable enabling us to later add to the dressDetails table
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


    public void SearchTheDatabase(View view) {
        /// method 1 for retrieving dress matcghing search criteria ///

        /// ******** JORDI commented out below  is how I have been trying to retrieve the image from the database *******

        /*Cursor c = myDB.rawQuery("SELECT profile_id, image1, designer, drycleaning FROM dressdetails INNER JOIN profile ON (" +
                "profile.id = dressdetails.profile_id) WHERE dressdetails.size LIKE '%" + size + "%' OR dressdetails.style LIKE '%" + style + "%' OR dressdetails.viel LIKE '%"
                + viel + "%';", null);*/

        /// Set cursor for any dress that match users input ///
        Cursor c = myDB.rawQuery("SELECT profile_id, image1, drycleaning FROM dressdetails INNER JOIN profile ON (" +
                "profile.id = dressdetails.profile_id) WHERE dressdetails.size LIKE '%" + size + "%' OR dressdetails.style LIKE '%" + style + "%' OR dressdetails.viel LIKE '%"
                + viel + "%';", null);
        if(c.moveToFirst()){
            do {
                // store it as a string in a variable
                dressesProfileId = c.getString(c.getColumnIndex("profile_id"));

                /// *** HERE is where I store the image into a variable *** ///
                queryImageRetrieved = c.getString(c.getColumnIndex("image1"));
                dryCleaningDetails = c.getString(c.getColumnIndex("drycleaning"));
            } while (c.moveToNext());
        }

        c.close();

        /// *** Here I try to convert the string back into a byte array *** ///
        byte[] bytes;
        try{
            bytes = queryImageRetrieved.getBytes("UTF-8"); BitmapFactory.decodeByteArray(bytes, 0, queryImageRetrieved.length());
            // Catch any I/O exceptions
            System.out.println("You have reached here and bytes has a value " + bytes);
        }catch (Exception e){
            e.printStackTrace();
        }


        /// method 2 for returning contact details of matching dress from profile table and storing in variables ///
        String rentorName = "";
        String rentorEmail = "";
        String rentalPrice = "";
        String query = "SELECT username, useremail, rentalprice FROM profile INNER JOIN dressdetails ON (" +
                "profile.id = dressdetails.profile_id) WHERE id = ?";
        Cursor c1 = myDB.rawQuery(query, new String[] { dressesProfileId });
        //Cursor c1 = myDB.rawQuery("SELECT username, useremail FROM profile INNER JOIN dressdetails ON (" +
                //"profile.id = dressdetails.profile_id) WHERE profile.id = ?", new String[] {dressesProfileId});
        if(c1.moveToFirst()) {
            // Store the values needed in variables so we can send it to the user in an email with the image
            do {
                rentorName = c1.getString(c1.getColumnIndex("username"));
                rentorEmail = c1.getString(c1.getColumnIndex("useremail"));
                rentalPrice = c1.getString(c1.getColumnIndex("rentalprice"));
                sellerContactDetails = "The name of the seller is " + rentorName + ", their email address is " + rentorEmail +
                        ", the rental price is " + rentalPrice + " and the dry cleaning cost is " + dryCleaningDetails + ";";

                // Check that it has worked - Debug tool
                Toast.makeText(this, sellerContactDetails, Toast.LENGTH_SHORT).show();

                // Continue until no more dresses match the set criteria
            } while (c1.moveToNext());
        }

        // Close the cursor and the database
        c1.close();
        myDB.close();


        // Send results to users email
        Log.i("Send email", "");

        String[] TO = { rentorEmail };
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("plain/text");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "P2P Weddings");
        emailIntent.putExtra(Intent.EXTRA_TEXT, sellerContactDetails);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email ", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SearchCriteria.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

        // Intent to pass to ResultsMessage Activity
        Intent startResultMessage = new Intent(SearchCriteria.this, ResultsMessage.class);
        startActivity(startResultMessage);

    }
}
