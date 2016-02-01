package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by michaelheneghan on 17/01/2016.
 */
public class ResultsMessage extends Activity {

    /// Initialisation of Activity EditTexts, Spinners, Buttons, Objects & Strings ///
    UserLocalStore userLocalStore;
    Button LogOutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.message);
        CustomFont.replaceDefaultFont(this, "DEFAULT", "lobster.ttf");
        UserLocalStore userLocalStore = new UserLocalStore(this);

        LogOutButton = (Button)findViewById(R.id.logOutButton);

        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(ResultsMessage.this, LogIn.class);
                Toast.makeText(ResultsMessage.this, "You have succesfully logged out, thanks for useing My P2P Weddings", Toast.LENGTH_SHORT).show();
                startActivity(logout);
            }
        });
    }

    public void Exit() {

        /// Clear user data and logout user ///
        //userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);

    }





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
