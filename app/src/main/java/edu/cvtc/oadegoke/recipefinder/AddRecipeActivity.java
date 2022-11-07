package edu.cvtc.oadegoke.recipefinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class AddRecipeActivity extends AppCompatActivity {

    // Variables to access the text views and button
    private EditText titleEditText;
    private EditText nationalityEditText;
    private EditText instructionsEditText;
    private Button buttonAddRecipe;

    // Variables to hold the Recipe's information
    private String title;
    private String nationality;
    private String instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // initializes the views and button
        titleEditText = findViewById(R.id.editText_addTitle);
        nationalityEditText = findViewById(R.id.editText_addNationality);
        instructionsEditText = findViewById(R.id.editText_addInstructions);
        buttonAddRecipe = findViewById(R.id.button_addRecipeToDB);

        // Creates a new instance of the database helper
        AddRecipeHelper dbHelper = new AddRecipeHelper(AddRecipeActivity.this);

        // Sets an onclick listener to the add recipe button
        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // gets the text the user typed from the text views
                title = titleEditText.getText().toString();
                nationality = nationalityEditText.getText().toString();
                instructions = instructionsEditText.getText().toString();

                // checks to see if title is empty or not
                if (TextUtils.isEmpty(title)) {
                    // Displays a toast
                    Toast.makeText(AddRecipeActivity.this, "Your recipe was not added, please enter a title", Toast.LENGTH_LONG).show();
                } else {
                    // Adds the new recipe to the database
                    MyRecipeInfo recipeInfo = new MyRecipeInfo(-1, title, nationality, instructions);

                    // Checks to see if the recipe was added or not
                    if (dbHelper.insertRecipe(recipeInfo)) {
                        // Displays a toast
                        Toast.makeText(AddRecipeActivity.this, "Your recipe was added", Toast.LENGTH_LONG).show();

                        // Clears the text boxes after the user adds a recipe
                        titleEditText.setText("");
                        nationalityEditText.setText("");
                        instructionsEditText.setText("");
                    } else {
                        // Displays a toast
                        Toast.makeText(AddRecipeActivity.this, "Your recipe was not added", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}