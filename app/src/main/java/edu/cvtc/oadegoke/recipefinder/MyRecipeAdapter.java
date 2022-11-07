package edu.cvtc.oadegoke.recipefinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.MyRecipeViewHolder> {

    // Variable to hold the array list of Recipe Info, context, and a layout inflater
    private Context mContext;
    private ArrayList<MyRecipeInfo> myRecipeInfoArrayList;
    private LayoutInflater mInflater;

    // Constructor that initializes the recipe list from the data
    MyRecipeAdapter(Context context, ArrayList<MyRecipeInfo> arrayList) {
        this.mContext = context;
        this.myRecipeInfoArrayList = arrayList;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates the recipe list item layout
        View itemView = mInflater.inflate(R.layout.my_recipe_item, parent, false);

        // Returns a viewHolder with the layout and the adapter
        return new MyRecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecipeViewHolder holder, int position) {
        // Gets the position of the list and sets it to a variable
        MyRecipeInfo recipe = myRecipeInfoArrayList.get(position);

        // gets the title and nationality from the current recipe and sets it to
        // their corresponding text views
        holder.myRecipeTitleView.setText(recipe.getMyRecipeTitle());
        holder.myRecipeNationalityView.setText(recipe.getMyRecipeNationality());

        // adds an onclick listener for the recycler view item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts a new activity to display the recipe details
                Intent intent = new Intent(mContext, MyRecipeDetails.class);

                intent.putExtra("myRecipeTitle", recipe.getMyRecipeTitle());
                intent.putExtra("myRecipeNationality", recipe.getMyRecipeNationality());
                intent.putExtra("myRecipeInstructions", recipe.getMyRecipeInstructions());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returns the size of the recipeInfoArrayList.
        return myRecipeInfoArrayList.size();
    }

    // Constructor that initializes the ViewHolder textViews
    // from the recipe item layout
    public class MyRecipeViewHolder extends RecyclerView.ViewHolder {
        public final TextView myRecipeTitleView;
        public final TextView myRecipeNationalityView;

        public MyRecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            myRecipeTitleView = itemView.findViewById(R.id.textView_myRecipeTitle);
            myRecipeNationalityView = itemView.findViewById(R.id.textView_myRecipeNationality);
        }
    }
}
