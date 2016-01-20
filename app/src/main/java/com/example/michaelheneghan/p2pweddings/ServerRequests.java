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

    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://cs385project.netau.net";

    public ServerRequests(){

    }

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

             //Server Communication part - it's relatively long but uses standard methods

             //Encoded String - we will have to encode string by our custom method (Very easy)
             String encodedStr = getEncodedData(dataToSend);

             //Will be used if we want to read some data from server
             BufferedReader reader = null;

             //Connection Handling
             try {
                 //Converting address String to URL
                 URL url = new URL(SERVER_ADDRESS + "Register.php");
                 //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
                 HttpURLConnection con = (HttpURLConnection) url.openConnection();

                 //Post Method
                 con.setRequestMethod("POST");
                 //To enable inputting values using POST method
                 //(Basically, after this we can write the dataToSend to the body of POST method)
                 con.setDoOutput(true);
                 OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                 //Writing dataToSend to outputstreamwriter
                 writer.write(encodedStr);
                 //Sending the data to the server - This much is enough to send data to server
                 //But to read the response of the server, you will have to implement the procedure below
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

             //Same return null, but if you want to return the read string (stored in line)
             //then change the parameters of AsyncTask and return that type, by converting
             //the string - to say JSON or user in your case
             return null;
         }
             /*
             ArrayList<NameValuePair> dataToSend = new ArrayList<>();
             dataToSend.add(new BasicNameValuePair("username", user.username));
             dataToSend.add(new BasicNameValuePair("password", user.password));

             HttpParams httpRequestParam = new BasicHttpParams();
             HttpConnectionParams.setConnectionTimeout(httpRequestParam, CONNECTION_TIMEOUT);
             HttpConnectionParams.setSoTimeout( httpRequestParam, CONNECTION_TIMEOUT);

             HttpClient client = new DefalutHttpClient(httpRequestParam);
             HttpPost post = new HttpPost(SERVER_ADDRESS, "Register.php");

             try{

                 post.setEntity(new UrlEncodedFormEntity(dataToSend));
                 client.execute(post);

             }catch(Exception e){
                e.printStackTrace();
             }


             return null;
         }

         @Override
         protected void onPostExecute(Void aVoid) {

             progressDialog.dismiss();
             userCallBack.done(null);
             super.onPostExecute(aVoid);
         }*/

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

            //Use HashMap, it works similar to NameValuePair
            Map<String,String> dataToSend = new HashMap<>();
            dataToSend.put("username", user.username);
            dataToSend.put("password", user.password);

            //Server Communication part - it's relatively long but uses standard methods

            //Encoded String - we will have to encode string by our custom method (Very easy)
            String encodedStr = getEncodedData(dataToSend);

            //Will be used if we want to read some data from server
            BufferedReader reader = null;

            //Connection Handling
            try {
                //Converting address String to URL
                URL url = new URL(SERVER_ADDRESS + "FetchUserData.php");
                //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                //Post Method
                con.setRequestMethod("POST");
                //To enable inputting values using POST method
                //(Basically, after this we can write the dataToSend to the body of POST method)
                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                //Writing dataToSend to outputstreamwriter
                writer.write(encodedStr);
                //Sending the data to the server - This much is enough to send data to server
                //But to read the response of the server, you will have to implement the procedure below
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

            //Same return null, but if you want to return the read string (stored in line)
            //then change the parameters of AsyncTask and return that type, by converting
            //the string - to say JSON or user in your case
            return null;

            /*
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.username));
            dataToSend.add(new BasicNameValuePair("password", user.password));

            HttpParams httpRequestParam = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParam, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParam, CONNECTION_TIMEOUT);

            HttpClient client = new DefalutHttpClient(httpRequestParam);
            HttpPost post = new HttpPost(SERVER_ADDRESS, "FetchUserData.php");

            User returnedUser = null;
            try{

                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = JSONObject(result);

                if(jObject.length() == 0){

                    returnedUser = null;

                }
                else{

                    String username = jObject.getString("username");
                    String password = jObject.getString("password");

                    returnedUser = new User(user.username, user.password);
                }

            }catch(Exception e){
                e.printStackTrace();
            }


            return returnedUser;*/
        }

        @Override
        protected void onPostExecute(User returnedUser) {

            progressDialog.dismiss();
            userCallBack.done(returnedUser);
            super.onPostExecute(returnedUser);
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

}

