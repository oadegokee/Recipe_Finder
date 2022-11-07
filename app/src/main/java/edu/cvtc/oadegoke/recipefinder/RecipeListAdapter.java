package edu.cvtc.oadegoke.recipefinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    // Variable to hold the array list of Recipe Info and a layout inflater
    private ArrayList<RecipeInfo> recipeInfoArrayList;
    private LayoutInflater mInflater;

    public class RecipeViewHolder extends RecyclerView.ViewHolder  {
        // variables for text views and image view in the recycler view
        public final TextView recipeTitleView, recipeNationalityView;
        public final ImageView recipeImageView;

        // variable for the adapter
        final RecipeListAdapter recipeAdapter;

        // Constructor that initializes the ViewHolder textViews and ImageView
        // from the recipe item layout and sets the adapter
        public RecipeViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeTitleView = itemView.findViewById(R.id.textView_RecipeTitle);
            recipeNationalityView = itemView.findViewById(R.id.textView_RecipeNationality);
            recipeImageView = itemView.findViewById(R.id.imageView_Recipe);
            this.recipeAdapter = adapter;
        }
    }

    // Constructor that initializes the recipe list from the data
    public RecipeListAdapter(Context context, ArrayList<RecipeInfo> recipeInfoArrayList) {
        mInflater = LayoutInflater.from(context);
        this.recipeInfoArrayList = recipeInfoArrayList;
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates the recipe list item layout
        View mItemView = mInflater.inflate(R.layout.recipe_item, parent, false);

        // Returns a viewHolder with the layout and the adapter
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder holder, int position) {
        // Gets the position of the list and sets it to a variable
        RecipeInfo currentRecipe = recipeInfoArrayList.get(position);

        // gets the title and nationality from the current recipe and sets it to
        // their corresponding text views
        holder.recipeTitleView.setText(currentRecipe.getTitle());
        holder.recipeNationalityView.setText(currentRecipe.getNationality());

        // sets the image from the provided url to the image view
        Picasso.get().load(currentRecipe.getImage()).into(holder.recipeImageView);

        // adds an onCick listener for the recycler view item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts a new activity to display the recipe details
                Intent intent = new Intent(view.getContext(), RecipeDetails.class);

                intent.putExtra("image", currentRecipe.getImage());
                intent.putExtra("title", currentRecipe.getTitle());
                intent.putExtra("nationality", currentRecipe.getNationality());
                intent.putExtra("instruction", currentRecipe.getInstructions());
                intent.putExtra("sourceLink", currentRecipe.getSourceLink());
                intent.putExtra("youtubeLink", currentRecipe.getYoutubeLink());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returns the size of the recipeInfoArrayList.
        return recipeInfoArrayList.size();
    }
}
