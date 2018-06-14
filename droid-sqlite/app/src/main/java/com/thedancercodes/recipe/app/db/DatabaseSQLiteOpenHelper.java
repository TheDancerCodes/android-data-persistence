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

    // We receive an instance of SQLite Database class as a parameter.
    // We will use it to provide queries that we need to create our individual tables.
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the Recipe table
        // This method take a string that contains the query for creating the table.
        // Instead of defining the string inline here, we add it to our RecipeContract class.
        db.execSQL(RecipeContract.CREATE_RECIPE_ENTRY_TABLE);

        // Create the RecipeStep Table
        db.execSQL(RecipeContract.CREATE_RECIPE_STEP_ENTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // We don't need to retain a users data, so we drop the existing tables &
        // then have them created again
        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeStepEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RecipeContract.RecipeEntry.TABLE_NAME);

        onCreate(db);

    }
}
