package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
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

    EditText UsernameInput;
    EditText passwordET;
    Button userSubmitButton, toRentalsActivity;
    Button passwordSubmitButton;
    ToggleButton registerCharTB;
    TextView showCharsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        /*Typeface myTpfc = Typeface.createFromAsset(getAssets(), "assets/fonts/lobster.ttf");
        showCharsTV = (TextView) findViewById(R.id.showCharsTV);
        showCharsTV.setTypeface(myTpfc);*/

        UsernameInput = (EditText) findViewById(R.id.UsernameInputET);
        final EditText passwordET = (EditText) findViewById(R.id.PasswordET);
        passwordSubmitButton = (Button) findViewById(R.id.passwordSubmitButton);
        registerCharTB = (ToggleButton) findViewById(R.id.registerCharTB);


        // Creates two shared preferences to store users username and password login information for the next time user enters app
        passwordSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a variable to store users password input
                SharedPreferences sharedPref = getSharedPreferences("passwordInfo", Context.MODE_PRIVATE);

                // Create an editor to convert users username and password
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", UsernameInput.getText().toString());
                editor.putString("password", passwordET.getText().toString());

                // Creating a variable to store users username input
                SharedPreferences sharedPref2 = getSharedPreferences("usernameInfo", Context.MODE_PRIVATE);


                SharedPreferences.Editor editor2 = sharedPref2.edit();
                editor.putString("username", UsernameInput.getText().toString());
                editor.putString("password", passwordET.getText().toString());

                // Displays message to screen to let user know information has been stored
                Toast.makeText(Register.this, "Username and Password Saved", Toast.LENGTH_LONG).show();

                // Intent to move to next activity upon click of login button
                try {
                    Class rentalsClass = Class.forName("com.example.michaelheneghan.p2pweddings.UserPreferences");
                    Intent startLogIn = new Intent(Register.this, rentalsClass);
                    startActivity(startLogIn);
                }catch (Exception e){
                    e.printStackTrace();
                }

                sharedPref = PreferenceManager.getDefaultSharedPreferences(Register.this);

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

}




