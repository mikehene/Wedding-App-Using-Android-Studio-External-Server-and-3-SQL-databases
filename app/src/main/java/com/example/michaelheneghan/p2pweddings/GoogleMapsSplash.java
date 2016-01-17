package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by michaelheneghan on 27/11/2015.
 */
public class GoogleMapsSplash extends Activity {

    private static int SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GoogleMapsSplash.this, MapsActivity.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }

}
