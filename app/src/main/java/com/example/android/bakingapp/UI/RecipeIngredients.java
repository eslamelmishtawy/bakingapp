package com.example.android.bakingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;
import com.example.android.bakingapp.Utility.LoadRecipe;
import com.example.android.bakingapp.Utility.NoOfCols;
import com.example.android.bakingapp.Utility.Recipe;
import com.example.android.bakingapp.Utility.Step;

import java.util.ArrayList;
import java.util.List;


// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class RecipeIngredients extends Fragment {
    // Define a new interface OnImageClickListener that triggers a callback in the host activity

    // Mandatory empty constructor
    public RecipeIngredients() {
    }


    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.recipe_ingredients, container, false);
    if(savedInstanceState == null) {
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_ingredient);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        IngredientAdapter mAdapter = new IngredientAdapter();
        recyclerView.setAdapter(mAdapter);
        Bundle b = getArguments();
        mAdapter.setIngredient(b.<Ingredient>getParcelableArrayList("ing"));
    }
        return rootView;

    }
}
