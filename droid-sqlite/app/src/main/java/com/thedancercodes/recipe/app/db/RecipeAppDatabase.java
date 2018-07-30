package com.thedancercodes.recipe.app.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.thedancercodes.recipe.app.models.Recipe;
import com.thedancercodes.recipe.app.models.RecipeStep;

@Database(entities = {Recipe.class, RecipeStep.class}, version = 2) // Increase the Database Version to 2 to trigger Room to use our migration.
public abstract class RecipeAppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "recipe_app";
    // Define a variable and default it to null
    private static RecipeAppDatabase INSTANCE = null;

    // Creating our first migration
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            // SQL Statements that will update the DB
            database.execSQL("ALTER TABLE recipe"
            + "ADD COLUMN number_of_stars INTEGER");
        }
    };

    // Method that retrieves RecipeAppDatabase Instance when needed
    public static RecipeAppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (RecipeAppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeAppDatabase.class,
                        DATABASE_NAME)
                        .addMigrations(MIGRATION_1_2) // Let Room know the Migration is available.
                        .build();
            }
        }
        return INSTANCE;

    }

    // Add abstract methods to expose the DAO’s.
    public abstract RecipeDao recipeDao();

    public abstract RecipeStepDao recipeStepDao();
}
