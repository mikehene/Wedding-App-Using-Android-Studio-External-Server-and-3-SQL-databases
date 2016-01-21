package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by michaelheneghan on 15/11/2015.
 */
public class Splash extends Activity {

    /// Initialisation of Activity EditTexts, Media, Spinners, Buttons & Strings ///
    MediaPlayer splashSong;
    @Override
    protected void onCreate(Bundle openingScreen) {
        super.onCreate(openingScreen);
        setContentView(R.layout.splash);


        //.create takes two parameters, the first is context. The current state of the Activity
        splashSong = MediaPlayer.create(Splash.this, R.raw.splashsong); //R.raw.match is the folder and name of the music file
        splashSong.start();

        // thread
        Thread timer = new Thread(){
            // needs a run method
          public void run(){
              try{
                  sleep(5000);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }finally {
                  Intent openMainActivity = new Intent("com.example.michaelheneghan.p2pweddings.MainActivity");
                  startActivity(openMainActivity);
              }
          }
        };
        timer.start(); // start is a method from the thread class
    }

    //This is where the splash class dies because we dont want to see it again, it takes up memory etc.
    @Override
    protected void onPause() {
        super.onPause();  //stops the song playing into the next screen
        splashSong.release(); //finishes the splash good for good, kills it until the app starts again. Finish means kill yourself
        finish();
    }
}
