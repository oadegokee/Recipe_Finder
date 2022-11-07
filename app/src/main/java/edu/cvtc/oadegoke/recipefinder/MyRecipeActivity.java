package edu.cvtc.oadegoke.recipefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecipeActivity extends AppCompatActivity {
    // Member variables
    private AddRecipeHelper mDbOpenHelper;
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mRecipesLayoutManager;
    private MyRecipeAdapter mRecipesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe);
        // initializes the database helper
        mDbOpenHelper = new AddRecipeHelper(this);

        // Sets a reference to the recipe list item layout
        mRecyclerItems = findViewById(R.id.my_recipe_list_items);
        mRecipesLayoutManager = new LinearLayoutManager(this);

        mRecyclerItems.setLayoutManager(mRecipesLayoutManager);

        // Calls the display recipe method
        displayRecipe();
    }

    // Method to display the recipe
    private void displayRecipe() {
        // Displays the recipes
        ArrayList<MyRecipeInfo> recipes = mDbOpenHelper.getRecipeInfo();
        mRecipesRecyclerAdapter = new MyRecipeAdapter(MyRecipeActivity.this, recipes);
        mRecyclerItems.setAdapter(mRecipesRecyclerAdapter);
    }
}