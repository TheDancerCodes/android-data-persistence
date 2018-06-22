package com.thedancercodes.recipe.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.thedancercodes.recipe.app.models.Recipe;
import com.thedancercodes.recipe.app.models.RecipeStep;

import java.util.ArrayList;
import java.util.List;

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

    // Method that will handle inserting records in our database.
    public void createRecipe(Recipe recipe) {

        // Create new ContentValues Object and store it in a local variable.
        ContentValues values = new ContentValues();

        // Take advantage of the put methods in-order to provide the information
        // that we want to have inserted
        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        // Execute the DB’s insert method in order to store the recipe.
        long rowId = database.insert(RecipeContract.RecipeEntry.TABLE_NAME, null, values);

        // Log the row ID of the newly inserted row, or -1 if an error occurred
        Log.d(TAG, "Recipe with id: " + rowId);

        // Get steps from a given Recipe
        List<RecipeStep> steps = recipe.getSteps();

        // Condition to check for steps in a recipe.
        // This is a precaution to avoid creating null pointer exceptions when we access a recipe
        // that doesn't have any steps.
        if (steps != null && steps.size() > 0) {

            // Loop over our steps and insert them one by one.
            for (RecipeStep step : steps) {
                createRecipeStep(step, rowId);
            }
        }
    }

    // Method that will handle inserting recipe steps in our database.
    public void createRecipeStep(RecipeStep recipeStep, long recipeId) {

        // Create new ContentValues Object and store it in a local variable.
        ContentValues values = new ContentValues();

        // Take advantage of the put methods in-order to provide the information
        // that we want to have inserted from our RecipeStep Object.
        values.put(RecipeContract.RecipeStepEntry.COLUMN_RECIPE_ID, recipeId);
        values.put(RecipeContract.RecipeStepEntry.COLUMN_INSTRUCTION, recipeStep.getInstruction());
        values.put(RecipeContract.RecipeStepEntry.COLUMN_STEP_NUMBER, recipeStep.getStepNumber());

        // Execute the DB’s insert method in order to store the Recipe Step.
        long rowId = database.insert(RecipeContract.RecipeStepEntry.TABLE_NAME, null, values);

        // Log the row ID of the newly inserted row, or -1 if an error occurred
        Log.d(TAG, "Recipe step with id: " + rowId);
    }

    // getAllRecipes() method, that we will use to return the list of our stored recipes.
    public  List<Recipe> getAllRecipes() {

        // Instantiate and return an empty list of recipes for now
        List<Recipe> recipes = new ArrayList<>();


        // selectQuery as a local variable: Contains our SELECT statement
        String selectQuery = "SELECT * FROM recipe";

        // Use rawQuery() to query the data.
        Cursor cursor = database.rawQuery(selectQuery, null);

        try {

            // While loop that uses the cursor's moveToNext() method as a condition
            while (cursor.moveToNext()){

                // Retrieve content from Cursor and store it inside Recipe's List
                Recipe recipe = new Recipe(
                        cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION)),
                        cursor.getInt(cursor.getColumnIndex(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID))
                );

                // Add Recipe ID
                recipe.setId(cursor.getLong(cursor.getColumnIndex(RecipeContract.RecipeEntry._ID)));

                // Add Recipe to Recipe's list
                recipes.add(recipe);
            }

        }
        finally {

            // Check whether Cursor is not null & not closed already
            if (cursor != null && !cursor.isClosed()) {

                // Close the Cursor
                cursor.close();
            }
        }

        return recipes;
    }

    public void updateRecipe(Recipe recipe) {

        // Use ContentValues Object to update records in DB
        ContentValues values = new ContentValues();

        values.put(RecipeContract.RecipeEntry.COLUMN_NAME, recipe.getName());
        values.put(RecipeContract.RecipeEntry.COLUMN_DESCRIPTION, recipe.getDescription());
        values.put(RecipeContract.RecipeEntry.COLUMN_IMAGE_RESOURCE_ID, recipe.getImageResourceId());

        // Create the selection string
        String selection = RecipeContract.RecipeEntry._ID + " = ?";

        // selectionArgs Array: Only has 1 item - the ID of the Recipe in string format.
        String[] selectionArgs = { String.valueOf(recipe.getId())};

        // Make call to DB's update method
        int count = database.update(RecipeContract.RecipeEntry.TABLE_NAME, values, selection, selectionArgs);

        // Log response to verify that one record is updated
        Log.d(TAG, "Number of records updated: " + count);
    }

    public void deleteRecipe(Recipe recipe) {

        // Create the selection string
        String selection = RecipeContract.RecipeEntry._ID + " = ?";

        // selectionArgs Array: Only has 1 item - the ID of the Recipe in string format.
        String[] selectionArgs = { String.valueOf(recipe.getId())};

        // Make call to DB's delete method
        int count = database.delete(RecipeContract.RecipeEntry.TABLE_NAME, selection, selectionArgs);

        // Log response to verify that one record is updated
        Log.d(TAG, "Number of records deleted: " + count);
    }
}
