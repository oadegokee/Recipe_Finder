package edu.cvtc.oadegoke.recipefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Variables
    private RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;
    private RequestQueue mRequestQueue;
    private ArrayList<RecipeInfo> recipeInfoArrayList;
    private ProgressBar progressBar;
    private EditText searchEditText;
    private ImageButton searchButton;
    private String recipes[] = {"chicken", "salad", "fish", "pie"};
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the floating action button by id, and sets an on Click Listener
        // to it.
        FloatingActionButton addRecipeButton = findViewById(R.id.add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {

            // Starts a new activity (Add Recipe Activity) when the floating
            // action button is clicked.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

        // Generates a random number
        randomNumber = new Random().nextInt((3) + 1);

        // Calls the get recipe info method to get a random
        // recipe search on load
        getRecipeInfo(recipes[randomNumber]);

        // initializes the views
        progressBar = findViewById(R.id.progress_bar);
        searchEditText = findViewById(R.id.editText_SearchRecipe);
        searchButton = findViewById(R.id.button_Search);

        // adds an onClickListener to the Search Button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sets the progress bar to be visible
                // showing that the results are loading
                progressBar.setVisibility(View.VISIBLE);

                // Checks to see if the text field is empty
                if (searchEditText.getText().toString().isEmpty()) {
                    // if empty, displays an error message
                    searchEditText.setError("Please enter a recipe to search for");
                    return;
                } else {
                    // if not empty, calls the get recipe info method
                    // to load the recipes from the api
                    getRecipeInfo(searchEditText.getText().toString());
                }
            }
        });
    }

    // method to load the recipes from the api
    private void getRecipeInfo(String searchQuery) {
        // creates a new arrayList
        recipeInfoArrayList = new ArrayList<>();

        // sets the request queue variable
        mRequestQueue = Volley.newRequestQueue(MainActivity.this);

        // clears the cache
        mRequestQueue.getCache().clear();

        // url to get data from the api and concatenates whatever the user
        // searches for
        String api = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + searchQuery;

        // creates a new request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // creates a json object request
        JsonObjectRequest recipeObjectRequest = new JsonObjectRequest(api, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // sets the progress bar to gone (not visible) when the data loads
                progressBar.setVisibility(View.GONE);

                // gets all the json data
                try {

                    JSONArray mealsArray = response.getJSONArray("meals");

                    for (int i = 0; i < mealsArray.length(); i++) {
                        JSONObject mealsObj = mealsArray.getJSONObject(i);
                        String image = mealsObj.optString("strMealThumb");
                        String title = mealsObj.optString("strMeal");
                        String nationality = mealsObj.optString("strArea");
                        String instructions = mealsObj.optString("strInstructions");
                        String sourceLink = mealsObj.optString("strSource");
                        String youtubeLink = mealsObj.optString("strYoutube");

                        // saves data in the recipe info class
                        RecipeInfo recipeInfo = new RecipeInfo(image, title, nationality, instructions, sourceLink, youtubeLink);

                        // adds the recipe info class to the array list
                        recipeInfoArrayList.add(recipeInfo);

                        // Get a handle to the RecyclerView
                        mRecyclerView = findViewById(R.id.recyclerView_Recipe);

                        // Create an adapter and supply the data to be displayed
                        mAdapter = new RecipeListAdapter(MainActivity.this, recipeInfoArrayList);

                        // Connect the adapter with the RecyclerView.
                        mRecyclerView.setAdapter(mAdapter);

                        // Give the RecyclerView a default layout manager.
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();

                    // displays an error message if nothing is gotten
                    // back from the API
                    searchEditText.setError("No recipe found, search for something else");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });

        // adds the object request to the request queue
        queue.add(recipeObjectRequest);
    }


    // Specifies the option menu for the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Identifies the menu item selected and starts the new activity
        // for the selected option

        switch (item.getItemId()) {
            case R.id.my_recipes:
                Intent intent = new Intent(this, MyRecipeActivity.class);
                startActivity(intent);
                return true;
            default:
                // do nothing
        }
        return super.onOptionsItemSelected(item);
    }
}