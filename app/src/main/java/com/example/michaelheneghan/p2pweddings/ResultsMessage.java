package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by michaelheneghan on 17/01/2016.
 */
public class ResultsMessage extends Activity {

    UserLocalStore userLocalStore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        UserLocalStore userLocalStore = new UserLocalStore(this);

    }

    public void LogOut(View view) {



        /// Clear user data and logout user ///
        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);

        /// Set intent to return to login page ///
        Intent logout = new Intent(ResultsMessage.this, LogIn.class);
        startActivity(logout);
        Toast.makeText(this, "You have succesfully logged out, thanks for useing My P2P Weddings", Toast.LENGTH_SHORT).show();

        ////////// Methods to check username and password and display to screen //////////

/*
    @Override
    protected void onStart() {
        super.onStart();

        if(authenticate() == true){
            displayUsersDetails();
        }
        else{
        startActivity(new Intent(ResultsMessage.this, LogIn.class));

    }

    /// method to authenticate user is logged in or out////
    private boolean authenticate(){

        return userLocalStore.getUserLoggedIn();

    }

    private void displayUsersDetails(){

        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etPassword.setText(user.password);


    }*/

    }
}
