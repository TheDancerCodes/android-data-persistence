package com.thedancercodes.recipe.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by TheDancerCodes on 15/06/2018.
 */
public class RecipeAppDataSource {

    // A common way of providing a unique tag for the various classes in your application.
    private static final String TAG = RecipeAppDataSource.class.getSimpleName();

    // 2 Fields: SQLiteDatabase instance & DatabaseSQLiteOpenHelper
    private SQLiteDatabase database;
    private DatabaseSQLiteOpenHelper dbHelper;

    // Initialize the DatabaseSQLiteOpenHelper class inside the constructor
    public RecipeAppDataSource(Context context) {
        this.dbHelper = new DatabaseSQLiteOpenHelper(context);
    }

    // open() method will get the writable DB from the DB Helper class
    public void open() {

        // Set this to our database instance field.
        // Now we have access to the SQLite DB throughout this entire class.
        this.database = dbHelper.getWritableDatabase();

        Log.d(TAG, "Database is opened");
    }

    // close() method will close the connection to the instance of the SQLite database.
    public void close() {
        dbHelper.close();

        Log.d(TAG, "Database is closed");
    }
}
