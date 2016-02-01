package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;

/**
 * Created by michaelheneghan on 23/11/2015.
 */
public class SearchCriteria extends Activity {

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    Spinner designerSpin, styleSpinner, sizeSpinner, vielSpin;
    String designerChoice, style, size, viel;
    String queryImageRetrieved, sellerContactDetails, dryCleaningDetails, dressesProfileId, emailReceivedFromDressDetails, emailReceivedFromUserPref;
    String rentorName = "";
    String rentorEmail = "";
    String rentalPrice = "";
    Uri bmpUri;

    /// Initiate database ///
    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.searchdatabase);
        CustomFont.replaceDefaultFont(this, "DEFAULT", "lobster.ttf");

        /// Open database ready for use ///
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

        emailReceivedFromDressDetails = getIntent().getExtras().getString("emailPassedFromDressDetails");
        emailReceivedFromUserPref = getIntent().getExtras().getString("emailPassedFromUserPref");

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

        /*Cursor c = myDB.rawQuery("SELECT profile_id, image1, designer, drycleaning FROM dressdetails INNER JOIN profile ON (" +
                "profile.id = dressdetails.profile_id) WHERE dressdetails.size LIKE '%" + size + "%' OR dressdetails.style LIKE '%" + style + "%' OR dressdetails.viel LIKE '%"
                + viel + "%';", null);*/

        /// Set cursor for any dress that match users input ///
        Cursor c = myDB.rawQuery("SELECT profile_id, image1, drycleaning FROM dressdetails INNER JOIN profile ON (" +
                "profile.id = dressdetails.profile_id) WHERE dressdetails.size LIKE '%" + size + "%' AND dressdetails.style LIKE '%" + style + "%' AND dressdetails.viel LIKE '%"
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

        /// *** Here I convert the string back into a byte array *** ///
        byte[] bytes;
        try{
            bytes = queryImageRetrieved.getBytes("UTF-8"); BitmapFactory.decodeByteArray(bytes, 0, queryImageRetrieved.length());
            // Catch any I/O exceptions
            System.out.println("You have reached here and bytes has a value " + bytes);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*String pathofBmp = null;
        try {
            pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), queryImageRetrieved,"title", null);
                bmpUri = Uri.parse(pathofBmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/


        /// method 2 for returning contact details of matching dress from profile table and storing in variables ///

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
                sendEmail();
                // Check that it has worked - Debug tool
                Toast.makeText(this, sellerContactDetails, Toast.LENGTH_SHORT).show();

                // Continue until no more dresses match the set criteria
            } while (c1.moveToNext());
        }

        // Close the cursor and the database
        c1.close();
        myDB.close();
    }

    public void sendEmail(){

        try {
            Log.i("Send email", "");
            String[] TO = { emailReceivedFromUserPref, emailReceivedFromDressDetails  };
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));

            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"tlysaght@cs.nuim.ie"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "P2P Weddings");
            emailIntent.putExtra(Intent.EXTRA_TEXT, sellerContactDetails);
            emailIntent.setType("plain/text");
            emailIntent.setType("image/jpeg");
            Uri uri = Uri.parse("file://" + queryImageRetrieved);
            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
            //System.out.println("You have reached here and uri has a value " + uri);
            //emailIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);  // This is another method for sending images via email
            startActivity(Intent.createChooser(emailIntent, "Send mail Using :"));
            finish();
            Log.i("Finished sending email ", "");
            Intent startResultMessage = new Intent(SearchCriteria.this, ResultsMessage.class);
            startActivity(startResultMessage);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SearchCriteria.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }
}
