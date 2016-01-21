package com.example.michaelheneghan.p2pweddings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelheneghan on 19/01/2016.
 */
public class ServerRequests extends Activity{

    /// Initialisation of Activity EditTexts, Spinners, Buttons & Strings ///
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://cs385project.netau.net";

    public ServerRequests(){

    }

    /// Constructor set ///
    public ServerRequests(Context context){

        progressDialog = new ProgressDialog(context);
        progressDialog .setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait...");

    }

    /// Methods to show the progress of the tasks and start Async class in background////
    public void storeUserDataInBackground(User user, GetUserCallBack userCallBack){

        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();

    }
    /// Methods to show the progress of the tasks ////
    public void fetchUserDataInBackground(User user, GetUserCallBack callBack){

        progressDialog.show();
        new FetchUserDataAsyncTask(user, callBack).execute();

    }

     /// Async task used to run in background ///
    public class StoreUserDataAsyncTask extends AsyncTask <Void, Void, Void>{

         User user;
         GetUserCallBack userCallBack;

         public StoreUserDataAsyncTask(User user, GetUserCallBack userCallBack){
             this.user = user;
             this.userCallBack = userCallBack;

         }

         @Override
         protected Void doInBackground(Void... params) {

             //Use HashMap, it works similar to NameValuePair
             Map<String,String> dataToSend = new HashMap<>();
             dataToSend.put("username", user.username);
             dataToSend.put("password", user.password);

             /// Server Communication ///

             /// Encoded String ///
             String encodedStr = getEncodedData(dataToSend);

             /// Used to read data from server ///
             BufferedReader reader = null;

             /// Connection Handling ///
             try {
                 //Converting address String to URL ///
                 URL url = new URL(SERVER_ADDRESS + "Register.php");
                 /// Opening the connection ///
                 HttpURLConnection con = (HttpURLConnection) url.openConnection();

                 //Post Method
                 con.setRequestMethod("POST");
                 /// To enable inputting values using POST method ///

                 con.setDoOutput(true);
                 OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                 /// Writing dataToSend to outputstreamwriter ///
                 writer.write(encodedStr);
                 /// Sending the data to the server ///
                 writer.flush();

                 //Data Read Procedure ///
                 StringBuilder sb = new StringBuilder();
                 reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                 String line;
                 while((line = reader.readLine()) != null) { //Read till there is something available
                     sb.append(line + "\n");     //Reading and saving line by line - not all at once
                 }
                 line = sb.toString();           //Saving complete data received in string ///

                 /// To check the values received in Logcat ///
                 Log.i("custom_check", "The values received in the store part are as follows:");
                 Log.i("custom_check",line);

             } catch (Exception e) {
                 e.printStackTrace();
             } finally {
                 if(reader != null) {
                     try {
                         reader.close();     //Closing the
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }
             return null;
         }


         private String getEncodedData(Map<String,String> data) {
             StringBuilder sb = new StringBuilder();
             for(String key : data.keySet()) {
                 String value = null;
                 try {
                     value = URLEncoder.encode(data.get(key),"UTF-8");
                 } catch (UnsupportedEncodingException e) {
                     e.printStackTrace();
                 }

                 if(sb.length()>0)
                     sb.append("&");

                 sb.append(key + "=" + value);
             }
             return sb.toString();
         }

     }

    public class FetchUserDataAsyncTask extends AsyncTask <Void, Void, User> {

        User user;
        GetUserCallBack userCallBack;

        public FetchUserDataAsyncTask(User user, GetUserCallBack userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;

        }

        @Override
        protected User doInBackground(Void... params) {

            /// Use HashMap ///
            Map<String,String> dataToSend = new HashMap<>();
            dataToSend.put("username", user.username);
            dataToSend.put("password", user.password);

            /// Server Communication ///

            /// Encoded String - I have to encode string by the custom method ///
            String encodedStr = getEncodedData(dataToSend);

            /// Will be used to read data from server //
            BufferedReader reader = null;

            //Connection Handling
            try {
                /// Converting address String to URL ///
                URL url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                /// Opening the connection ///
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                /// Post Method ///
                con.setRequestMethod("POST");
                /// To enable inputting values using POST method ///
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                /// Writing dataToSend to outputstreamwriter ///
                writer.write(encodedStr);
                //Sending the data to the server - This much is enough to send data to server
                //But to read the response of the server, I have to implemented the procedure below
                writer.flush();

                //Data Read Procedure - Basically reading the data comming line by line
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while((line = reader.readLine()) != null) { //Read till there is something available
                    sb.append(line + "\n");     //Reading and saving line by line - not all at once
                }
                line = sb.toString();           //Saving complete data received in string, you can do it differently

                //Just check to the values received in Logcat
                Log.i("custom_check", "The values received in the store part are as follows:");
                Log.i("custom_check",line);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(reader != null) {
                    try {
                        reader.close();     //Closing the
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return null;

        }

        /// Method to show dialog box and close ///
        @Override
        protected void onPostExecute(User returnedUser) {

            progressDialog.dismiss();
            userCallBack.done(returnedUser);
            super.onPostExecute(returnedUser);
        }

        /// Converts encoded data to a string ///
        private String getEncodedData(Map<String,String> data) {
            StringBuilder sb = new StringBuilder();
            for(String key : data.keySet()) {
                String value = null;
                try {
                    value = URLEncoder.encode(data.get(key),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if(sb.length()>0)
                    sb.append("&");

                sb.append(key + "=" + value);
            }
            return sb.toString();
        }

    }

}

