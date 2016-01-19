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

    }
}
