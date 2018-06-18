package com.thedancercodes.recipe.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.thedancercodes.recipe.app.models.Recipe;
import com.thedancercodes.recipe.app.models.RecipeStep;

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

}
