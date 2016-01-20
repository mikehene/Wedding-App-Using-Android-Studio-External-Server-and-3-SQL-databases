package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;

import java.net.InterfaceAddress;

/**
 * Created by michaelheneghan on 19/01/2016.
 */
interface GetUserCallBack{

    ///  Interface to inform activity calling Server Request that the activity is completed ///

    public abstract void done(User returnedUser);




}
