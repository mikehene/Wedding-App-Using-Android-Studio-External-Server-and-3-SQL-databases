package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * Created by michaelheneghan on 16/11/2015.
 */
public class LogIn extends Activity{

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    EditText UsernameInputET, LogInPasswordET;
    ToggleButton charTB;
    Button logInButton;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.login);
        CustomFont.replaceDefaultFont(this, "DEFAULT", "lobster.ttf");

        UsernameInputET = (EditText) findViewById(R.id.UsernameInputET);
        LogInPasswordET = (EditText) findViewById(R.id.LogInPasswordET);
        charTB = (ToggleButton) findViewById(R.id.charTB);
        logInButton = (Button) findViewById(R.id.LogInlogInButton);
        UserLocalStore userLocalStore = new UserLocalStore(this);


        charTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!charTB.isChecked()){
                    LogInPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else{
                    LogInPasswordET.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

    }

    public void logInButtonEntered(View view) {


        String username = UsernameInputET.getText().toString();
        String password = LogInPasswordET.getText().toString();
        if((username == null || username.equals(""))){
            Toast.makeText(LogIn.this, "You must enter a Username", Toast.LENGTH_SHORT).show();
        }
        else if(password == null || password.equals("")) {
            Toast.makeText(LogIn.this, "You must enter a Password", Toast.LENGTH_SHORT).show();
        }else {
            User user = new User(username, password);
            authenticate(user);

        }

    }

    private void authenticate(User user ){

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                    Intent DressDetailsIntent = new Intent(LogIn.this, SearchCriteria.class);
                    startActivity(DressDetailsIntent);
                }
            }
        });


    }

    private void showErrorMessage(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LogIn.this);
        dialogBuilder.setMessage("Incorrect User Details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();

    }

    private void logUserIn(User returnedUser){

        userLocalStore.storeLUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

    }

    public void passToRegister(View view) {

        Intent passToRegisterActivity = new Intent(LogIn.this, Register.class);
        startActivity(passToRegisterActivity);
        startActivity(new Intent(this, SearchCriteria.class));

    }
}
