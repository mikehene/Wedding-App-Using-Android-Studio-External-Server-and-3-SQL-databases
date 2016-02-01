package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by michaelheneghan on 18/01/2016.
 */
public class UserLocalStore extends Activity{

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){

        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);

    }

    public void storeLUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();

    }

}
