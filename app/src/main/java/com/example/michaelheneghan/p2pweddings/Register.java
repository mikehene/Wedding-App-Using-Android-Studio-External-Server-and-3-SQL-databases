package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by michaelheneghan on 16/11/2015.
 */
public class Register extends Activity{

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    EditText UsernameInput, passwordET;
    Button userSubmitButton, toRentalsActivity, passwordSubmitButton;
    ToggleButton registerCharTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        /*Typeface myTpfc = Typeface.createFromAsset(getAssets(), "assets/fonts/lobster.ttf");
        showCharsTV = (TextView) findViewById(R.id.showCharsTV);
        showCharsTV.setTypeface(myTpfc);*/

        UsernameInput = (EditText) findViewById(R.id.UsernameInputET);
        passwordET = (EditText) findViewById(R.id.PasswordET);
        passwordSubmitButton = (Button) findViewById(R.id.passwordSubmitButton);
        registerCharTB = (ToggleButton) findViewById(R.id.registerCharTB);


        // Creates two shared preferences to store users username and password login information for the next time user enters app
        passwordSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Store users username and password into string variables from ET //
                String username = UsernameInput.getText().toString();
                String password = passwordET.getText().toString();

                // Instantiate user object and pass in retrieved username and password //
                User user = new User(username, password);

                // Call method from ServerRequest class to register user //
                registerUser(user);

                // Intent to move to next activity upon click of login button
                try {
                    Class rentalsClass = Class.forName("com.example.michaelheneghan.p2pweddings.UserPreferences");
                    Intent startLogIn = new Intent(Register.this, rentalsClass);
                    startActivity(startLogIn);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        });

        // Enables user to hide or show password characters - Default set to hide characters
        registerCharTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!registerCharTB.isChecked()) {
                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    private void registerUser(User user){

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, UserPreferences.class ));
            }
        });

    }

    public void passToLogIn(View view) {

        Intent passToLogInAct = new Intent(Register.this, LogIn.class);
        startActivity(passToLogInAct);

    }
}




