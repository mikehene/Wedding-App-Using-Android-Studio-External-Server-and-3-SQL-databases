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

    Spinner designerSpin, searchRentBuySpin, styleSpinner, sizeSpinner, vielSpin;
    String designerChoice, rentBuy, style, size, viel, emailReceivedfromdressdetails;
    String queryIdRetrieved, sellerContactDetails;

    SQLiteDatabase myDB = null;
    SQLiteQueryBuilder query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchdatabase);
        myDB = this.openOrCreateDatabase("ProfileDB", MODE_PRIVATE, null);


        //designerSpin = (Spinner) findViewById(R.id.designer_Spin);
        searchRentBuySpin = (Spinner) findViewById(R.id.rentBuySpin);
        styleSpinner = (Spinner) findViewById(R.id.styleSpin);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpin);
        vielSpin = (Spinner) findViewById(R.id.vielSpinner);

        //chooseDisgnerSpinner();
        //addListenerDesignerSpinner();
        addSearchRentBuySpinner();
        addListenerRentBuy();
        addStyleSpinner();
        addListenerStyleSpinner();
        addSizeSpinner();
        addListenerSizeSpinner();
        addSearchVielSpinner();
        addListenerVielSpinner();

    }

    public void addSearchRentBuySpinner() {

        searchRentBuySpin = (Spinner) findViewById(R.id.rentBuySpin);

        ArrayAdapter<CharSequence> rentBuyAdapter =
                ArrayAdapter.createFromResource(this,
                    R.array.RentBuy,
                        android.R.layout.simple_spinner_item);

        rentBuyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    public void addListenerRentBuy(){

        searchRentBuySpin = (Spinner) findViewById(R.id.rentBuySpin);

        searchRentBuySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                rentBuy = itemSelectedInSpinner;
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


/*

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
*/
    public void SearchTheDatabase(View view) {
        // method 1 for retrieving dress matcghing search criteria

        /*
        // Set cursor for any dress that match users input
        Cursor c = myDB.rawQuery("SELECT image AS imageForEmailing FROM dressdetails INNER JOIN dressdetails ON (" +
                "profile.id = dressdetails.profile_id) WHERE dressdetails.rentbuy LIKE '%" + rentBuy + "%' OR " +
                "dressdetails.size LIKE '%" + size + "%' OR dressdetails.style LIKE '%" + style + "%' OR dressdetails.viel LIKE '%"
                + viel + "%';", null);
        if(c.moveToFirst()){
            do {
                // store it as a string in a variable
                queryIdRetrieved = c.getString(c.getColumnIndex("image"));
            } while (c.moveToNext());
        }
        c.close();


        // Convert the string back into a byte array and decode
        try{
            byte[] bytes = queryIdRetrieved.getBytes("UTF-8");
            BitmapFactory.decodeByteArray(bytes, 0, queryIdRetrieved.length());
            // Catch any I/O exceptions
        }catch (Exception e){
            e.printStackTrace();
        }


        // method 2 for returning contact details of matching dress
        String rentorName = "";
        String rentorEmail = "";
        String rentalprice;
        Cursor c1 = myDB.rawQuery("SELECT username, useremail, rentalprice FROM profile where id = " + Integer.parseInt(queryIdRetrieved) + ";", null);
        Cursor c2 = myDB.rawQuery("SELECT username, useremail, rentalprice FROM profile where id = ?", new String[] {queryIdRetrieved});
        if(c1.moveToFirst()) {
            // Store the values needed in variables so we can send it to the user in an email with the image
            do {
                rentorName = c1.getString(c.getColumnIndex("username"));
                rentorEmail = c1.getString(c.getColumnIndex("useremail"));
                rentalprice = c1.getString(c.getColumnIndex("rentalprice"));
                sellerContactDetails = "The name of the seller is " + rentorName + " their email address is " + rentorEmail +" and the cost per" +
                        "day is " + rentalprice + ";";

                // Check that it has worked - Debug tool
                Toast.makeText(this, sellerContactDetails, Toast.LENGTH_SHORT).show();

                // Continue until no more dresses match the set criteria
            } while (c1.moveToNext());
        }

        // Close the cursor and the database
        c.close();
        myDB.close();


        // Send results to users email
        Log.i("Send email", "");

        String[] TO = { rentorEmail };
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


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
*/
        // Intent to pass to ResultsMessage Activity
        Intent startResultMessage = new Intent(SearchCriteria.this, ResultsMessage.class);
        startActivity(startResultMessage);

    }
}
