package edu.cvtc.oadegoke.recipefinder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.cvtc.oadegoke.recipefinder.AddRecipeDatabaseContract.AddRecipeInfoEntry;

public class AddRecipeHelper extends SQLiteOpenHelper {
    // Member Constants
    public static final String DATABASE_NAME = "RecipeFinder_oadegoke.db";
    public static final int DATABASE_VERSION = 1;

    // Constructor
    public AddRecipeHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is referenced
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates a new table
        db.execSQL(AddRecipeInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(AddRecipeInfoEntry.SQL_CREATE_INDEX1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    // A method that gets the items from the database
    ArrayList<MyRecipeInfo> getRecipeInfo() {
        // Creates an instance of the Recipe info class
        // in an array list
        ArrayList<MyRecipeInfo> recipeList = new ArrayList<>();

        // selects the data from the database
        String query = "select * from " + AddRecipeInfoEntry.TABLE_NAME;

        // Gets a readable database
        SQLiteDatabase db = this.getReadableDatabase();

        // gets the data from the db
        Cursor cursor = db.rawQuery(query, null);

        // Checks to see if the there were items in database
        if (cursor.moveToFirst()) {
            // loops through the results and creates new recipe objects
            do {
                int recipeID = Integer.parseInt(cursor.getString(0));
                String recipeTitle = cursor.getString(1);
                String recipeNationality = cursor.getString(2);
                String recipeInstructions = cursor.getString(3);

                // inserts the object into the recipe list
                recipeList.add(new MyRecipeInfo(recipeID, recipeTitle, recipeNationality, recipeInstructions));

            } while (cursor.moveToNext());

        } else {
            // do nothing
        }

        // closes the cursor
        cursor.close();

        // returns the recipe list
        return recipeList;
    }

    // Adds the recipe to the database
    public Boolean insertRecipe(MyRecipeInfo myRecipe) {
        // Gets a writable database from the SQLiteDatabase class
        SQLiteDatabase mDb = this.getWritableDatabase();

        // Creates an instance of the Content Value class and
        // stores the data in pairs (column name, value)
        ContentValues values = new ContentValues();
        values.put(AddRecipeInfoEntry.COLUMN_RECIPE_TITLE, myRecipe.getMyRecipeTitle());
        values.put(AddRecipeInfoEntry.COLUMN_RECIPE_NATIONALITY, myRecipe.getMyRecipeNationality());
        values.put(AddRecipeInfoEntry.COLUMN_RECIPE_INSTRUCTIONS, myRecipe.getMyRecipeInstructions());

        // Inserts the values into the database
        long newRowId = mDb.insert(AddRecipeInfoEntry.TABLE_NAME, null, values);

        // Checks to see if values were added to the database
        if (newRowId == -1) {
            // Was not added
            return false;
        } else {
            // Was added
            return true;
        }
    }

    public Boolean deleteRecipe(String title) {
        // Gets a writable database from the SQLiteDatabase class
        SQLiteDatabase mDb = this.getWritableDatabase();

        // Deletes the data from the database
        long result = mDb.delete(AddRecipeInfoEntry.TABLE_NAME, "recipe_title=?", new String[] {title});

        // Checks to see there were values selected
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
