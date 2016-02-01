package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
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
import java.util.List;

public class MapsActivity extends Activity {

    // Constant for defining latitude and longitude
    // This is the correct lat and long for Maynooth but for some reason it is showing in Germany when I run it on my device //
    static final LatLng MaynoothPos = new LatLng(53.3816, 6.5910);

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
                        position(MaynoothPos).title("University"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void onSearch(View view) {
        EditText location_tf = (EditText) findViewById(R.id.TFaddress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);


            } catch (IOException e) {
                e.printStackTrace();
            }

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }


    public void changeType(View view) {
        if (googleMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}


