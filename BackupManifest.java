<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.michaelheneghan.p2pweddings" >

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />

    <permission
        android:name="com.example.michaelheneghan.p2pweddings.permission.MAPS_RECEIVE"
        android:protectionLevel="signature" />
    <uses-feature
        android:glEsVersion="0x00020000"
        android:required="true"/>
    <!-- Enables me to use openGL -->

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/ActivityThemeLightPurple" >

        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />

        <activity
            android:name=".Splash"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="com.example.michaelheneghan.p2pweddings.MainActivity" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name=".Register"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="com.example.michaelheneghan.p2pweddings.REGISTER" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name=".LogIn"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="com.example.michaelheneghan.p2pweddings.LOGIN" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name=".UserPreferences"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.USERPREFERENCES" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name=".DressDetails"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.DRESSDETAILS" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
        <activity
            android:name=".GoogleMapsSplash"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.GOOGLEMAPSSPALSH" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <meta-data
            android:name="com.google.android.maps.v2.API_KEY"
            android:value="@string/google_maps_key" />

        <activity
            android:name=".MapsActivity"
            android:label="@string/title_activity_maps" >
        </activity>

        <activity
            android:name=".SearchCriteria"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.SEARCHCRITERIA" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <activity
            android:name=".ResultsMessage"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.RESULTSMESSAGE" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <activity
            android:name=".ServerRequests"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.SERVERREQUESTS" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <activity
            android:name=".UserLocalStore"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.USERLOCALSTORE" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <activity
            android:name=".User"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.USER" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <activity
            android:name=".MyDialogFragment"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MYDIALOGFRAGMENT" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>

        <activity
            android:name=".GetUserCallBack"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.GETUSERCALLBACK" />

                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>


        <intent-filter>
            <action android:name="android.intent.action.SEND " />
            <category android:name="android.intent.category.DEFAULT" />
            <data android:mimeType="image/*" />
        </intent-filter>

    </application>

</manifest>
