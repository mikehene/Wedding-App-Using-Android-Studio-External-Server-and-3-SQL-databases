package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.DialogInterface;
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
import android.widget.Toast;

import java.io.File;

/**
 * Created by michaelheneghan on 23/11/2015.
 */
public class UserPreferences extends Activity {
    EditText nameET, addressET, emailET, viewProfileET, salePriceET, deleteDressEt, rentalPriceET;
    Button createDatabase, deleteDressBut;
    Spinner rentBuySpinner;
    private String rentbuy;

    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rentalsql);
        nameET = (EditText) findViewById(R.id.nameET);
        addressET = (EditText) findViewById(R.id.addressET);
        emailET = (EditText) findViewById(R.id.emailET);
        //viewProfileET = (EditText) findViewById(R.id.viewProfileET);
        //deleteDressEt = (EditText) findViewById(R.id.deleteDressET);
        createDatabase = (Button) findViewById(R.id.createDatabase);
        //deleteDressBut = (Button) findViewById(R.id.deleteDressBut);
        salePriceET = (EditText) findViewById(R.id.salePriceET);
        rentalPriceET = (EditText) findViewById(R.id.rentalPriceET);

        // Call methods to create database and spinners on activity creation
        createDatabase(null);
        addRentBuySpinner();
        addListenerRentBuySpinner();

    }


    public void createDatabase(View view) {

        // Open or Create myDB database
        // Pass the database name, designate that only this app can use it
        // and a DatabaseErrorHandler in the case of database corruption

        try {
            //  saleprice VARCHAR, saleprice, rentalprice VARCHAR, rentalprice, dressSalePriceET + "', '" + dressRentalPriceET +

            myDB = this.openOrCreateDatabase("ProfileDB",
                    MODE_PRIVATE, null);

            // Exectute SQL statement to create the profile table

            myDB.execSQL("CREATE TABLE IF NOT EXISTS profile " +
                    "(id integer primary key AUTOINCREMENT, username VARCHAR, useraddress VARCHAR, useremail VARCHAR, rentbuy VARCHAR, saleprice VARCHAR, rentalprice VARCHAR);");

            File database = getApplicationContext().getDatabasePath("ProfileDB.db");

            // Check if the database exists
            if (!database.exists()) {
                Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Database Missing", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {

            Log.e("Profile error", "Error Creating Database");

        }

        // Prepopulate table with some examples
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('mikehene', 'home', 'mikehene@gmail.com', 'Rent', " +
                "'€0', '€100');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('farrahbu', 'home', 'farrahbu87@gmail.com', 'Rent', " +
                "'€0', '€25');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('hanSolo', 'home', 'hansolo@gmail.com', 'Rent', " +
                "'€0', '€50');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('lukeSkywalker', 'home', 'lukeSkywalker@gmail.com', 'Rent', " +
                "'€0', '€75');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('princessLeia', 'home', 'princessLeia@gmail.com', 'Rent', " +
                "'€0', '€25');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('chewbacca', 'home', 'chewbacca@gmail.com', 'Rent', " +
                "'€0', '€50');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('C3PO', 'home', 'C3PO@gmail.com', 'Rent', " +
                "'€0', '€100');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('biWanKenobi', 'home', 'obiWanKenobi@gmail.com', 'Rent', " +
                "'€0', '€75');");
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('yoda', 'home', 'yoda@gmail.com', 'Rent', " +
                "'€0', '€50');");
    }




    public void InsertIntoDatabase(View view) {

        //Get the details entered by the user and store in variables
        String userName = nameET.getText().toString();
        String userAddress = addressET.getText().toString();
        String userEmail = emailET.getText().toString();
        String rentBuy = rentbuy;
        String dressSalePriceET = salePriceET.getText().toString();
        String dressRentalPriceET = rentalPriceET.getText().toString();

        //Execute SQL statement to insert new data
        myDB.execSQL("INSERT INTO profile (username, useraddress, useremail, rentbuy, saleprice, rentalprice) VALUES ('" + userName + "', '" +
                userAddress + "', '" + userEmail + "', '" + rentBuy + "', '" + dressSalePriceET + "', '" + dressRentalPriceET + "');");

        if(rentBuy == "Buy"){
            Intent myIntent = new Intent(UserPreferences.this,SearchCriteria.class);
            startActivity(myIntent);
        }
        try {

            Intent myIntent2 = new Intent(UserPreferences.this,DressDetails.class);
            myIntent2.putExtra("idPassed",getId(userEmail));
            startActivity(myIntent2);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private String getId(String email){
        String selectQuery = "SELECT id FROM profile WHERE useremail = ?";
        Cursor c = myDB.rawQuery(selectQuery, new String[]{email});
        String tempId = "";
        if (c.moveToFirst()) {
            tempId = c.getString(c.getColumnIndex("id"));
        }
        c.close();
        return tempId;
    }

    public void addRentBuySpinner(){

        rentBuySpinner = (Spinner) findViewById(R.id.rentBuySpinner);

        // Create Adapter for spinner and pass in the xml file with user options
        ArrayAdapter<CharSequence> rentBuySpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.RentBuy,
                        android.R.layout.simple_spinner_item);

        // Set how the options will be displayed to the user
        rentBuySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to spinner
        rentBuySpinner.setAdapter(rentBuySpinnerAdapter);

    }

    public void addListenerRentBuySpinner(){

        rentBuySpinner = (Spinner) findViewById(R.id.rentBuySpinner);

        // Store users choice in a variable enabling us to later add to the profile table
        rentBuySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                rentbuy = itemSelectedInSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getProfile(View view) {

        // Use a cursor to provide read and write access to database results
        Cursor cursor = myDB.rawQuery("SELECT * FROM profile", null);

        // Retrieve the index for the column name provided
        int idColumn = cursor.getColumnIndex("id");
        int nameColumn = cursor.getColumnIndex("username");
        int addressColumn = cursor.getColumnIndex("useraddress");
        int emailColumn = cursor.getColumnIndex("useremail");
        int rentBuyColumn = cursor.getColumnIndex("rentbuy");

        // Move to the first row of results
        cursor.moveToFirst();

        // Create empty string to append results to
        String profileList = "";

        // Verify that the user has inputted information
        if(cursor != null && (cursor.getCount() > 0)){

            do{
                // Get the results and store them in a String
                String id = cursor.getString(idColumn);
                String name = cursor.getString(nameColumn);
                String address = cursor.getString(addressColumn);
                String email = cursor.getString(emailColumn);
                String rentbuy = cursor.getString(rentBuyColumn);

                profileList = profileList + id + " : " + name + " : " + email + " : " + address + " : " + rentbuy + "\n";

                // Keep getting results as long as they exist
            }while(cursor.moveToNext());

            viewProfileET.setText(profileList);
        }

        else{

            Toast.makeText(this, "No profile exists", Toast.LENGTH_SHORT).show();
            viewProfileET.setText("Nothing selected");

        }

    }

    // Delete an individual dress from database using primary key
    public void deleteDressBut(View view) {

        // Retrieve the emailaddress of the the user to delete their dress
        String useremail = emailET.getText().toString();

        // Delete the dress from the database using email primary key
        myDB.execSQL("DELETE FROM profile WHERE useremail = " + useremail + ";");

        // Confirm to user action has been taken
        Toast.makeText(this, "Dress linked with " + useremail + " has been deleted from the database", Toast.LENGTH_SHORT).show();

    }




    // Close database when app is shutdown
    @Override
    protected void onDestroy() {

        myDB.close();

        super.onDestroy();
    }


    // Unused method for app designer to delete database, no option for user to do this. Administrator access only
    public void deleteDatabase(View view) {

        this.deleteDatabase("ProfileDB");

        File database = getApplicationContext().getDatabasePath("ProfileDB.db");
        if (database.exists()) {
            Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Database Missing", Toast.LENGTH_SHORT).show();
        }

    }
}



