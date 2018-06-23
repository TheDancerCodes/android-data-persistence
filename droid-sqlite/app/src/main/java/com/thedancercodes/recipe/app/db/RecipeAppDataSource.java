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

    public RecipeAppDataSource(Context context) {

    }


    // Method that will handle inserting records in our database.
    public void createRecipe(Recipe recipe) {

        long rowId = -1;


        // Log the row ID of the newly inserted row, or -1 if an error occurred
        Log.d(TAG, "createRecipe: the id: " + rowId);

    }

    // getAllRecipes() method, that we will use to return the list of our stored recipes.
    public  List<Recipe> getAllRecipes() {

        // Instantiate and return an empty list of recipes for now
        List<Recipe> recipes = new ArrayList<>();

        return recipes;
    }
}
