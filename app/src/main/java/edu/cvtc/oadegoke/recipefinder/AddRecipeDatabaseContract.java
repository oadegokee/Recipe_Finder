package edu.cvtc.oadegoke.recipefinder;

import android.provider.BaseColumns;

public class AddRecipeDatabaseContract {
    private AddRecipeDatabaseContract() {}

    public static final class AddRecipeInfoEntry implements BaseColumns {
        // Constants to hold the table name and column names
        public static final String TABLE_NAME = "recipe_info";
        public static final String COLUMN_RECIPE_TITLE = "recipe_title";
        public static final String COLUMN_RECIPE_NATIONALITY = "recipe_nationality";
        public static final String COLUMN_RECIPE_INSTRUCTIONS = "recipe_instructions";

        // Constant that creates the index
        public static final String INDEX1 = TABLE_NAME + "_index1";
        public static final String SQL_CREATE_INDEX1 =
                "CREATE INDEX " + INDEX1 + " ON " + TABLE_NAME +
                        "(" + COLUMN_RECIPE_TITLE + ")";

        // Constant that creates the table
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_RECIPE_TITLE + " TEXT NOT NULL, " +
                        COLUMN_RECIPE_NATIONALITY + " TEXT, " +
                        COLUMN_RECIPE_INSTRUCTIONS + " TEXT)";
    }
}
