package com.example.android.bakingapp.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;
import com.example.android.bakingapp.Utility.Recipe;
import com.example.android.bakingapp.Utility.Step;
import com.example.android.bakingapp.Widget.CollectionAppWidgetProvider;

import java.util.ArrayList;
import java.util.List;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.MasterListAdapterViewHolder> {


    private List<Recipe> recipe;
    private static String recipePositionName = "Brownies";
    private final MasterListAdapterOnClickHandler mClickHandler;
    private  Context context;
    public interface MasterListAdapterOnClickHandler {
        void onClick(ArrayList<Step> s, ArrayList<Ingredient> r);
    }

    public MasterListAdapter( MasterListAdapterOnClickHandler clickHandler ) {
        mClickHandler = clickHandler;
    }




    public class MasterListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public MasterListAdapterViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_recipe_card_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(recipe.get(adapterPosition).getSteps(), recipe.get(adapterPosition).getIngredients());
            setRecipePositionName(recipe.get(adapterPosition).getName());
            CollectionAppWidgetProvider.sendRefreshBroadcast(context);
        }

    }

    @Override
    public MasterListAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.items_recipe_card;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MasterListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MasterListAdapter.MasterListAdapterViewHolder trailersAdapterViewHolder, int i) {
        String recipee = recipe.get(i).getName();
        trailersAdapterViewHolder.mTextView.setText(recipee);
    }

    @Override
    public int getItemCount() {
        if (null == recipe) return 0;
        return recipe.size();
    }

    public void setRecipe(List<Recipe> recipee) {
        recipe = recipee;
        notifyDataSetChanged();
    }

    public static String getRecipePositionName(){
        return  recipePositionName;
    }
    public void setRecipePositionName(String rec){
        recipePositionName = rec;

    }

}
