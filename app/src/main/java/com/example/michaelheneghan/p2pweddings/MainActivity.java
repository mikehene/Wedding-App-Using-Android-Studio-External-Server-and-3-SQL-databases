package com.example.michaelheneghan.p2pweddings;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.Locale;


public class MainActivity extends Activity{

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    Button registerButton, LogInButton;
    Spinner themeSpinner;
    ToggleButton musicTB;
    MediaPlayer music;

    // create string for available themes
    String themes = "ActivityThemeBisque/ActivityThemeDarkPurple/ActivityThemeLightPurple/ActivityThemePastelGreen";

    @Override
    /// Method called upon opening of Activity ///
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// Method call to set theme of activity according to user choice in Profile Activity ///
        themeUtils.onActivityCreateSetTheme(this);

        /// Below calls which XML layout to show next ///
        setContentView(R.layout.activity_main);

        /// Calling custom font class to add imported font (in asset folder) to app globally///
        CustomFont.replaceDefaultFont(this, "DEFAULT", "lobster.ttf");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            DialogFragment myFragment = new MyDialogFragment();
            myFragment.show(getFragmentManager(), "theDialog");

            return true;

        }else if(id == R.id.exit_the_app){

            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
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

}
