package com.example.android.bakingapp.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class RecipeStuffFragment extends Fragment implements RecipeAdapter.RecipeAdapterViewHolderOnClickHandler{
    private List<Recipe> recipe;
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    onClickListenerRecipe mCallback;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface onClickListenerRecipe {
        void onCardSelectedRecipe(int s);
    }
    // Mandatory empty constructor
    public RecipeStuffFragment() {
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_recipe_stuff, container, false);
        // Get a reference to the GridView in the fragment_master_list xml layout file
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_stuff);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecipeAdapter mAdapter = new RecipeAdapter(RecipeStuffFragment.this);
        recyclerView.setAdapter(mAdapter);
        // Return the root view
        return rootView;
    }

    @Override
    public void onClick(int s) {
        mCallback.onCardSelectedRecipe(s);
    }
}


