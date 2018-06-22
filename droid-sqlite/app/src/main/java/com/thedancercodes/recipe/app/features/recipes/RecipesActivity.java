package com.thedancercodes.recipe.app.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.thedancercodes.recipe.app.R;
import com.thedancercodes.recipe.app.db.RecipeAppDataSource;
import com.thedancercodes.recipe.app.db.RecipesDataProvider;
import com.thedancercodes.recipe.app.models.Recipe;

import java.util.List;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;
    private RecipeAppDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        // Create a new instance of the RecipeAppDataSource class.
        dataSource = new RecipeAppDataSource(this);

        setupRecyclerView();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();

        // Make a call to dataSource.open() method:
        // This is how we make a database connection that we can use inside this Activity.
        dataSource.open();

        // Iterate over our list of recipes from the RecipesDataProvider &
        // then pass them one by one to the DataSources’s createRecipe() method.
        for (Recipe recipe : RecipesDataProvider.recipesList) {

            // Call through to our createRecipe method on the dataSource class.
            dataSource.createRecipe(recipe);
        }

        // Store List of recipes in a variable - allRecipes
        List<Recipe> allRecipes = getRecipes();

        // Delete all records
        dataSource.deleteAllRecipes();

        // Make another call to getRecipes() so that we can retrieve all Recipes from DB again.
        getRecipes();
    }

    private List<Recipe> getRecipes() {

        // Call to the dataSource.getAllRecipes() method, to get list of Recipes.
        List<Recipe> allRecipes = dataSource.getAllRecipes();
    for (Recipe recipe : allRecipes) {
      Log.i(TAG, "The Recipe: " + recipe);
    }
        // Pass list of recipes to the recycler view adapter
    adapter.setRecipes(allRecipes);

    return allRecipes;
    }

    @Override
    protected void onPause ()
    {
        super.onPause();

        // Make a call to the dataSource.close() method:
        // Whenever we are not interacting with this particular activity.
        dataSource.close();
    }

    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter( this );
        recipesRecyclerView.setAdapter( adapter );
    }

}
