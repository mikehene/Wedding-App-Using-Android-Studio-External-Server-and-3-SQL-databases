package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by michaelheneghan on 23/01/2016.
 */
public class themeUtils {

    private static int cTheme;


    public final static int ActivityColorLightPurple = 0;

    public final static int ActivityColorDarkPurple = 1;

    public final static int ActivityColorBisque = 2;

    public final static int ActivityColorPastelGreen = 3;

    public static void changeToTheme(Activity activity, int theme)

    {

        cTheme = theme;

        activity.finish();



        activity.startActivity(new Intent(activity, activity.getClass()));


    }

    public static void onActivityCreateSetTheme(Activity activity)

    {

        switch (cTheme)

        {

            case ActivityColorLightPurple:

                activity.setTheme(R.style.ActivityThemeLightPurpleTheme);

                break;

            case ActivityColorBisque:

                activity.setTheme(R.style.ActivityThemeBisqueTheme);

                break;

            case ActivityColorDarkPurple:

                activity.setTheme(R.style.ActivityThemeDarkPurpleTheme);

                break;

            case ActivityColorPastelGreen:

                activity.setTheme(R.style.ActivityThemePastelGreenTheme);

                break;

        }

    }


}
