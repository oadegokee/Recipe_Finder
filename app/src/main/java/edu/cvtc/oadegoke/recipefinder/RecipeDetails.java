package edu.cvtc.oadegoke.recipefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class RecipeDetails extends AppCompatActivity {

    // Variables to hold the Recipe's information
    private String image;
    private String title;
    private String nationality;
    private String instructions;
    private String sourceLink;
    private String youtubeLink;

    // Variables to access the image view, text views and buttons
    private ImageView recipeImageView;
    private TextView titleTextView;
    private TextView nationalityTextView;
    private TextView instructionsTextView;
    private Button sourceButton;
    private Button youtubeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // initializes the views
        recipeImageView = findViewById(R.id.imageView_Recipe);
        titleTextView = findViewById(R.id.textView_RecipeTitle);
        nationalityTextView = findViewById(R.id.textView_RecipeNationality);
        instructionsTextView = findViewById(R.id.textView_RecipeInstructions);
        sourceButton = findViewById(R.id.button_source);
        youtubeButton = findViewById(R.id.button_youtube);

        // gets the data from the Recipe adapter
        image = getIntent().getStringExtra("image");
        title = getIntent().getStringExtra("title");
        nationality = getIntent().getStringExtra("nationality");
        instructions = getIntent().getStringExtra("instruction");
        sourceLink = getIntent().getStringExtra("sourceLink");
        youtubeLink = getIntent().getStringExtra("youtubeLink");

        // sets data to views
        Picasso.get().load(image).into(recipeImageView);
        titleTextView.setText(title);
        nationalityTextView.setText(nationality);
        instructionsTextView.setText(instructions);

        // adds on click listener to the source button
        sourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Displays a snackbar if the link is empty
                if (sourceLink.isEmpty()) {
                    Snackbar sourceSnackbar = Snackbar.make(view, "There is no source", Snackbar.LENGTH_LONG);
                    sourceSnackbar.show();
                    return;
                }

                // Opens the link in the app
                Uri sourceUri = Uri.parse(sourceLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, sourceUri);
                startActivity(intent);
            }
        });

        // adds on click listener to the youtube button
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Displays a snackbar if the link is empty
                if (youtubeLink.isEmpty()) {
                    Snackbar youtubeSnackbar = Snackbar.make(view, "There is no youtube video", Snackbar.LENGTH_LONG);
                    youtubeSnackbar.show();
                    return;
                }

                // Opens the link in the app
                Uri youtubeUri = Uri.parse(youtubeLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, youtubeUri);
                startActivity(intent);
            }
        });
    }
}