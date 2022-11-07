package edu.cvtc.oadegoke.recipefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyRecipeDetails extends AppCompatActivity {
    // Variables to hold the Recipe's information
    private String myRecipeTitle;
    private String myRecipeNationality;
    private String myRecipeInstructions;

    // Variables to access the views and buttons
    private EditText titleEditText;
    private EditText nationalityEditText;
    private EditText instructionsEditText;
    private Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_details);

        // sets a reference to the dbHelper
        AddRecipeHelper dbHelper = new AddRecipeHelper(MyRecipeDetails.this);

        // initializes the views and button
        titleEditText = findViewById(R.id.textView_myRecipeTitle);
        nationalityEditText = findViewById(R.id.textView_myRecipeNationality);
        instructionsEditText = findViewById(R.id.textView_myRecipeInstructions);
        deleteButton = findViewById(R.id.button_delete);

        // gets the data from the Recipe adapter
        myRecipeTitle = getIntent().getStringExtra("myRecipeTitle");
        myRecipeNationality = getIntent().getStringExtra("myRecipeNationality");
        myRecipeInstructions = getIntent().getStringExtra("myRecipeInstructions");

        // sets data to views
        titleEditText.setText(myRecipeTitle);
        nationalityEditText.setText(myRecipeNationality);
        instructionsEditText.setText(myRecipeInstructions);

        // Sets an onclick listener to the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checks to see if the recipe is deleted
                if (dbHelper.deleteRecipe(myRecipeTitle)) {
                    // Displays a toast
                    Toast.makeText(MyRecipeDetails.this, "Your recipe was deleted", Toast.LENGTH_SHORT).show();

                    // Clears the text views after the user deletes the recipe
                    titleEditText.setText("");
                    nationalityEditText.setText("");
                    instructionsEditText.setText("");

                } else {
                    // Displays a toast if not deleted
                    Toast.makeText(MyRecipeDetails.this, "Your recipe was not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}