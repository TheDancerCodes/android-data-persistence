package com.thedancercodes.recipe.app.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.thedancercodes.recipe.app.models.Recipe;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createRecipe(Recipe recipe); // This method returns the recipe Id
}
