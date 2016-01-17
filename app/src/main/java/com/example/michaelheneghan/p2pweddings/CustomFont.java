package com.example.michaelheneghan.p2pweddings;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

/**
 * Created by michaelheneghan on 04/12/2015.
 */
public class CustomFont {

    public static void replaceDefaultFont(Context context, String nameOfFontBeingReplaced, String nameOfCustomFont){

        Typeface customFontTypeface = Typeface.createFromAsset(context.getAssets(), nameOfCustomFont);

        replaceFont(nameOfFontBeingReplaced, nameOfCustomFont);

    }

    private static void replaceFont(String nameOfFontBeingReplaced, String nameOfCustomFont) {
        try {
            Field myField = Typeface.class.getDeclaredField(nameOfFontBeingReplaced);
            myField.setAccessible(true);
            myField.set(null, nameOfCustomFont);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
