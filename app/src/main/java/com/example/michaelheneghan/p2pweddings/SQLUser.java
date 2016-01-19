package com.example.michaelheneghan.p2pweddings;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michaelheneghan on 27/11/2015.
 */
public class SQLUser {
/*


//////// THIS CLASS WAS CREATED ORIGINALLY TO IMPLEMENT AND MANIPULATE THE DATABASE BUT CHOSE TO IMPLEMENT IN ANOTHER WAY ////////////
///////////////////////////////////////////////PLEASE IGNORE//////////////////////////////////////////////////////////////



        public static final String KEY_ROWID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_ADDRESSID = "addressid";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_RENTBUY = "rent/buy";
        public static final String KEY_NOOFDRESSES = "1";
        public static final String KEY_BACKGROUNDCOLOUR = "blue";
        public static final String KEY_TURNMUSICONOFF = "on/off";


        private static final String DATABASE_NAME = "profile";
        private static final String DATABASE_TABLE = "userPreferences";
        private static final int DATABASE_VERSION = 1;



        protected DBHelper ourHelper;
        private final Context ourContext;
        private SQLiteDatabase ourPreferences;


        private static class DBHelper extends SQLiteOpenHelper {
            public DBHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                                KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                KEY_NAME + " TEXT NOT NULL, " +
                                KEY_ADDRESSID + " TEXT NOT NULL, " +
                                KEY_EMAIL + " TEXT NOT NULL, " +
                                KEY_RENTBUY + " TEXT NOT NULL, " +
                                KEY_NOOFDRESSES + " TEXT NOT NULL, " +
                                KEY_BACKGROUNDCOLOUR + " TEXT NOT NULL, " +
                                KEY_TURNMUSICONOFF + " TEXT NOT NULL);"
                );
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
                onCreate(db);
            }
        }

        public SQLUser(Context c){
            ourContext = c;
        }

        public SQLUser open(){
            ourHelper = new DBHelper(ourContext);
            ourPreferences = ourHelper.getWritableDatabase();
            return this;
        }

        public void close(){
            ourHelper.close();
        }

        public long createEntry(String name, String address, String email, String rentbuy, int noofdresses, String backgroundcolour, String turnsonmusic) {

            ContentValues cv = new ContentValues();
            cv.put(KEY_NAME, name);
            cv.put(KEY_ADDRESSID, address);
            cv.put(KEY_EMAIL, email);
            cv.put(KEY_RENTBUY, rentbuy);
            cv.put(KEY_NOOFDRESSES, noofdresses);
            cv.put(KEY_BACKGROUNDCOLOUR, backgroundcolour);
            cv.put(KEY_TURNMUSICONOFF, turnsonmusic);

            return ourPreferences.insert(DATABASE_TABLE, null, cv);
        }
*/
    }


