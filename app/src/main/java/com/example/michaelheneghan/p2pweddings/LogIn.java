package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

/*
 * Created by michaelheneghan on 16/11/2015.
 */
public class LogIn extends Activity{

    EditText UsernameInputET;
    EditText LogInPasswordET;
    ToggleButton charTB;
    Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        UsernameInputET = (EditText) findViewById(R.id.UsernameInputET);
        LogInPasswordET = (EditText) findViewById(R.id.LogInPasswordET);
        charTB = (ToggleButton) findViewById(R.id.charTB);
        logInButton = (Button) findViewById(R.id.LogInlogInButton);


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

        try {
            Class registerClass = Class.forName("com.example.michaelheneghan.p2pweddings.DressDetails");
            Intent startRegister = new Intent(LogIn.this, SearchCriteria.class);
            startActivity(startRegister);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}