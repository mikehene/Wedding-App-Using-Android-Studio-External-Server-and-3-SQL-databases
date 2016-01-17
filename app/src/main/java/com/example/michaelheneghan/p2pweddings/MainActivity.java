package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    // Create variables
    Button registerButton, LogInButton;
    Spinner themeSpinner;
    ToggleButton musicTB;
    MediaPlayer music;

    // create string for available themes
    String themes = "ActivityThemeBisque/ActivityThemeDarkPurple/ActivityThemeLightPurple/ActivityThemePastelGreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CustomFont.replaceDefaultFont(this, "DEFAULT", "lobsterregular.ttf");

        registerButton = (Button) findViewById(R.id.registerButton);
        LogInButton = (Button) findViewById(R.id.LogInButton);
        musicTB = (ToggleButton) findViewById(R.id.musicTB);
        //themeSpinner = (Spinner) findViewById(R.id.themeSpinner);

        music = MediaPlayer.create(MainActivity.this, R.raw.splashsong);music.start();

         //Create a class intent to move to next activity when register button is clicked
        registerButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    try {
                        Class registerClass = Class.forName("com.example.michaelheneghan.p2pweddings.Register");
                        Intent startRegister = new Intent(MainActivity.this, registerClass);
                        startActivity(startRegister);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
        });

        //Create a class intent to move to next activity when login button is clicked
        LogInButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    Class logInClass = Class.forName("com.example.michaelheneghan.p2pweddings.LogIn");
                    Intent startLogIn = new Intent(MainActivity.this, logInClass);
                    startActivity(startLogIn);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Enables user to switch on/off organ in-app music
        musicTB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(musicTB.isChecked()){
                    Toast.makeText(getApplicationContext(), "Background Music Started!", Toast.LENGTH_SHORT).show();
                    music.start();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Background Music Paused!", Toast.LENGTH_SHORT).show();
                    music.pause();
                }
            }
        });
    }
    //How to create an intent using Intent
    public void onClick(View v){
                    try {
                        Class registerClass = Class.forName("com.example.michaelheneghan.p2pweddings.Register");
                        Intent startRegister = new Intent(MainActivity.this, Register.class);
                        startActivity(startRegister);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
    /*
    public void changeTheme(View view) {

        ArrayAdapter<CharSequence> themeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.RentBuy,
                        android.R.layout.simple_spinner_item);

        themeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        themeSpinner.setAdapter(themeSpinnerAdapter);

    }

    public void addListenerThemeSpinner(){

        themeSpinner = (Spinner) findViewById(R.id.themeSpinner);

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(position).toString();
                themes = itemSelectedInSpinner;
                ;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }*/
}
