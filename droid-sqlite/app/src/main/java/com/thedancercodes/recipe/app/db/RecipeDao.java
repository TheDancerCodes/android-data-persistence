package com.thedancercodes.recipe.app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.thedancercodes.recipe.app.models.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe); // This method returns the recipe Id

    // Add query method to DAO - To return a list of recipe objects
    @Query("SELECT * FROM recipe")
    List<Recipe> getAllRecipes();
}
