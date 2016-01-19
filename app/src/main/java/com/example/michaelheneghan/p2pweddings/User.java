package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;

/**
 * Created by michaelheneghan on 18/01/2016.
 */
public class User extends Activity {

    /// Class and constructor to creat user object for login/registery ///
    String name, username, password;

    public User(String username, String password){

        this.username = username;
        this.password = password;

    }

}
