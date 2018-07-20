package com.thedancercodes.recipe.app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.thedancercodes.recipe.app.models.RecipeStep;

import java.util.List;

@Dao
public interface RecipeStepDao {

    // using void as we aren't interested in the resulting IDs of the inserted recipe step objects.
    @Insert
    void insertAll(List<RecipeStep> steps);

    // Add query method to DAO - To return a list of recipe step objects
    @Query("SELECT * FROM recipe_steps WHERE recipe_id = :recipeId")
    List<RecipeStep> getAllRecipeStepsByRecipeId(long recipeId);

}
