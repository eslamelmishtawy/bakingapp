package com.example.android.bakingapp.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;
import com.example.android.bakingapp.Utility.Step;

import java.util.ArrayList;
import java.util.List;

class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {


    private ArrayList<Ingredient> recipe;

    public IngredientAdapter() {
    }




    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView mQuantity;
        public TextView mIngredient;
        public TextView mMeasure;
        public IngredientAdapterViewHolder(View view){
            super(view);
            mQuantity = (TextView) view.findViewById(R.id.quantity);
            mIngredient = (TextView) view.findViewById(R.id.ingredient);
            mMeasure = (TextView) view.findViewById(R.id.measure);
        }
    }

    @Override
    public IngredientAdapter.IngredientAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new IngredientAdapter.IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.IngredientAdapterViewHolder trailersAdapterViewHolder, int i) {
        String quant = Double.toString(recipe.get(i).getQuantity());
        String meas = recipe.get(i).getMeasure();
        String in = recipe.get(i).getIngredient();
        trailersAdapterViewHolder.mQuantity.setText(quant);
        trailersAdapterViewHolder.mMeasure.setText(meas);
        trailersAdapterViewHolder.mIngredient.setText(in);
    }

    @Override
    public int getItemCount() {
        if (null == recipe) return 0;
        return recipe.size();
    }

    public void setIngredient(ArrayList<Ingredient> ing) {
        recipe = ing;
    }

}
