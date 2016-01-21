package com.example.michaelheneghan.p2pweddings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by michaelheneghan on 18/01/2016.
 */
public class MyDialogFragment extends DialogFragment{

    // Class created to inform user when theme had been changed but button to toggle themes not created due to time ///

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());

        theDialog.setTitle("Settings Options");

        theDialog.setMessage("You may change the theme of the app under your profile");

        theDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Clicked ok", Toast.LENGTH_LONG).show();
            }
        });

        theDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Clicked cancel", Toast.LENGTH_LONG).show();
            }
        });


        return super.onCreateDialog(savedInstanceState);
    }
}
