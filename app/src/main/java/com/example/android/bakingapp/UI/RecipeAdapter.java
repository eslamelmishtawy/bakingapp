package com.example.android.bakingapp.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Recipe;
import com.example.android.bakingapp.Utility.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder> {


    private List<Step> recipe;
    private final RecipeAdapterViewHolderOnClickHandler mClickHandler;

    public interface RecipeAdapterViewHolderOnClickHandler {
        void onClick(int recipeName);
    }

    public RecipeAdapter( RecipeAdapterViewHolderOnClickHandler clickHandler ) {
        mClickHandler = clickHandler;
    }




    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public RecipeAdapterViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_recipe_card_description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(adapterPosition);
        }

    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_stuff;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeAdapterViewHolder trailersAdapterViewHolder, int i) {
        String recipee = recipe.get(i).getShortDescription();
        trailersAdapterViewHolder.mTextView.setText(recipee);
    }

    @Override
    public int getItemCount() {
        if (null == recipe) return 0;
        return recipe.size();
    }

    public void setRecipeDescription(List<Step> recipeDescription) {
        recipe = recipeDescription;

    }

}
