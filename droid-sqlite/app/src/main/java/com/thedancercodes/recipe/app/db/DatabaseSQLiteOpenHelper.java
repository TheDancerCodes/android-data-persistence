package com.thedancercodes.recipe.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * SQLite Open Helper class is useful for heavy lifting;
 * Creating and Updating database only when needed.
 */
public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    // Constants
    private static final String DATABASE_NAME = "recipe_app.db";
    private static final int VERSION_NUMBER = 1;


    // Constructor:
    // Use the 2 constants defined above to replace the variables in the constructor
    // as they will never be modified by any outside callers of the class.
    public DatabaseSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
