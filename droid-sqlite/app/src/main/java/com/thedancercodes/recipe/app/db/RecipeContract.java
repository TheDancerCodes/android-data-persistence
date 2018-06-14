package com.thedancercodes.recipe.app.db;

import android.provider.BaseColumns;

/**
 * Contract Class:
 * A container class that holds the constants we need to define the table names & columns.
  */
final class RecipeContract {

    // Constructor
    private RecipeContract() {
    }

    // String that corresponds to our CREATE statement.
    // It contains the definitions of all the key columns: id, name, description & imageResourceId
    static final String CREATE_RECIPE_ENTRY_TABLE =
            "CREATE TABLE " + RecipeEntry.TABLE_NAME +
                    " ( " +
                    RecipeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    RecipeEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    RecipeEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                    RecipeEntry.COLUMN_IMAGE_RESOURCE_ID + " INTEGER NOT NULL, " +
                    "UNIQUE ( " + RecipeEntry._ID + ") ON CONFLICT REPLACE )";



    // Reference fields to the Recipe class
    private long id;

    private String name;

    private String description;

    private int imageResourceId;

    // RecipeEntry class implements BaseColumns Interface
    public static class RecipeEntry implements BaseColumns {

        // Set up column names for all the key fields in the recipe table.
        // NB: We don't add one for the ID because we get it for free in the BaseColumns class.
        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE_RESOURCE_ID = "image_resource_id";

    }


    // RecipeStepEntry class implements BaseColumns Interface
    public static class RecipeStepEntry implements BaseColumns {

        // The column names to include here are based on fields included in the model class.
        // In the RecipeStep class we will see these fields as members.
        public static final String TABLE_NAME = "recipe_step";
        public static final String COLUMN_RECIPE_ID = "recipe_id"; // Foreign key
        public static final String COLUMN_STEP_NUMBER = "step_number";
        public static final String COLUMN_INSTRUCTION = "instruction";
    }

}
