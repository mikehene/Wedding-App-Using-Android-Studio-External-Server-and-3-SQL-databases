package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by michaelheneghan on 23/11/2015.
 */
public class SearchCriteria extends Activity {

    Spinner designerSpin, rentbuySPin, styleSpinner, sizeSpinner, vielSpin;
    String designerChoice, rentBuy, style, size, viel;

    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchdatabase);


        designerSpin = (Spinner) findViewById(R.id.designer_Spin);
        rentbuySPin = (Spinner) findViewById(R.id.rentbuySpin);
        styleSpinner = (Spinner) findViewById(R.id.styleSpin);
        sizeSpinner = (Spinner) findViewById(R.id.sizeSpin);
        vielSpin = (Spinner) findViewById(R.id.SearchViel);

        chooseDisgnerSpinner();
        addListenerDesignerSpinner();
        addRentBuySpinner();
        addListenerRentBuySpinner();
        addStyleSpinner();
        addListenerStyleSpinner();
        addSizeSpinner();
        addListenerSizeSpinner();
        addSearchVielSpinner();
        addListenerVielSpinner();

    }

    public void chooseDisgnerSpinner() {
        designerSpin = (Spinner) findViewById(R.id.designer_Spin);
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

    public void addRentBuySpinner() {
        rentbuySPin = (Spinner) findViewById(R.id.rentbuySpin);
        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> rentBuySpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.DesignerChoice,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        rentBuySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        rentbuySPin.setAdapter(rentBuySpinnerAdapter);
    }

    public void addListenerRentBuySpinner() {

        rentbuySPin = (Spinner) findViewById(R.id.rentbuySpin);

        // Store users choice in a variable enabling us to later add to the dressDetails table
        rentbuySPin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        vielSpin = (Spinner) findViewById(R.id.SearchViel);
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

        vielSpin = (Spinner) findViewById(R.id.SearchViel);

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
    public void SearchTheDatabase(View view) {

        String query = ("SELECT * FROM profile INNER JOIN dressdetails ON (" +
                "profile.id = dressdetails.profile_id) WHERE dressdetails.rentbuy LIKE '%" + rentBuy + "%' AND dressdetails.designerchoice LIKE '%" +
                designerChoice + "%' AND dressdetails.size LIKE '%" + size + "%' AND dressdetails.style LIKE '%" + style + "%' AND dressdetails.viel LIKE '%"
                + viel + "%';");

    }

*/
}
