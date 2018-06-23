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

    // DAO fields
    private final RecipeDao recipeDao;
    private final RecipeStepDao recipeStepDao;

    public RecipeAppDataSource(Context context) {

        // Get the RecipeAppDatabase instance using the getInstance() method
        RecipeAppDatabase database = RecipeAppDatabase.getInstance(context);

        // Get access to DAOs
        recipeDao = database.recipeDao();
        recipeStepDao = database.recipeStepDao();
    }


    // Method that will handle inserting records in our database.
    public void createRecipe(Recipe recipe) {

        // Create Recipe
        long rowId = recipeDao.createRecipe(recipe);

        // Get Steps
        List<RecipeStep> steps = recipe.getSteps();

        // Check that we have steps to insert
        if (steps != null) {
            for (RecipeStep step : steps) {
                // Set our Recipe ID to each recipe step
                step.setRecipeId(rowId);
            }

            recipeStepDao.insertAll(steps);
        }


        // Log ID of the created Recipe
        Log.d(TAG, "createRecipe: the id: " + rowId);

    }

    // getAllRecipes() method, that we will use to return the list of our stored recipes.
    public  List<Recipe> getAllRecipes() {

        // Instantiate and return an empty list of recipes for now
        List<Recipe> recipes = new ArrayList<>();

        return recipes;
    }
}
