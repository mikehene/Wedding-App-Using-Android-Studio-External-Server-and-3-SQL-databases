package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
/*

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/
import java.io.IOException;
import java.io.InputStream;

public class MapsActivity extends Activity {

    // Constant for defining latitude and longitude
    static final LatLng DerekPos = new LatLng(40 , -79);

    // GoogleMap class
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // verify we can interact with the Google Map
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            // Show a satellite map with roads
            /* MAP_TYPE_NORMAL: Basic map with roads.
            MAP_TYPE_SATELLITE: Satellite view with roads.
            MAP_TYPE_TERRAIN: Terrain view without roads.
            */
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            // Place dot on current location
            googleMap.setMyLocationEnabled(true);

            // Turns traffic layer on
            googleMap.setTrafficEnabled(true);

            // Enables indoor maps
            googleMap.setIndoorEnabled(true);

            // Turns on 3D buildings
            googleMap.setBuildingsEnabled(true);

            // Show Zoom buttons
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            // Create a marker in the map at a given position with a title
            Marker marker = googleMap.addMarker(new MarkerOptions().
                    position(DerekPos).title("Hello"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
