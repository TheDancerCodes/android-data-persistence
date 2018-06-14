package com.thedancercodes.recipe.app.db;

import android.provider.BaseColumns;

final class RecipeContract
{
    private RecipeContract() {}

    public static class RecipeStepEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "recipe_step";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_STEP_NUMBER = "step_number";
        public static final String COLUMN_INSTRUCTION = "instruction";
    }

}
