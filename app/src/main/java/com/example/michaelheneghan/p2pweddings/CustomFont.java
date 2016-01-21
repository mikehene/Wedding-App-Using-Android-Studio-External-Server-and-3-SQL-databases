package com.example.michaelheneghan.p2pweddings;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by michaelheneghan on 04/12/2015.
 */
public class CustomFont {

    /// Class to implement custom font Creating Typeface and then a method called in the OnCreate Main method ///
    public static void replaceDefaultFont(Context context, String nameOfFontBeingReplaced, String nameOfCustomFont){

        Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), nameOfCustomFont);

        replaceFont(nameOfFontBeingReplaced, customFontTypeface);

    }

    private static void replaceFont(String nameOfFontBeingReplaced, Typeface customFontTypeface) {
        try {
            Field myField = Typeface.class.getDeclaredField(nameOfFontBeingReplaced);
            myField.setAccessible(true);
            myField.set(null, customFontTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
