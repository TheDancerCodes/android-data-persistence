package com.thedancercodes.recipe.app.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.thedancercodes.recipe.app.models.Recipe;
import com.thedancercodes.recipe.app.models.RecipeStep;

@Database(entities = {Recipe.class, RecipeStep.class}, version = 1)
public abstract class RecipeAppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "recipe_app";
    // Define a variable and default it to null
    private static RecipeAppDatabase INSTANCE = null;

    // Method that retrieves RecipeAppDatabase Instance when needed
    public static RecipeAppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (RecipeAppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeAppDatabase.class,
                        DATABASE_NAME)
                        .build();
            }
        }
        return INSTANCE;

    }

    // Add abstract methods to expose the DAO’s.
    public abstract RecipeDao recipeDao();

    public abstract RecipeStepDao recipeStepDao();
}
