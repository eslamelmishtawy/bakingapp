package com.example.android.bakingapp.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
public class MasterListFragment extends Fragment implements MasterListAdapter.MasterListAdapterOnClickHandler{
    private List<Recipe> recipe;


    // Mandatory empty constructor
    public MasterListFragment() {
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        int mNoOfColumns = NoOfCols.calculateNoOfColumns(rootView.getContext());
        // Get a reference to the GridView in the fragment_master_list xml layout file
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_card);
        GridLayoutManager layoutManager
                = new GridLayoutManager(rootView.getContext(), mNoOfColumns, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final MasterListAdapter mAdapter = new MasterListAdapter(MasterListFragment.this);
        recipe = LoadRecipe.loadRecipe(rootView.getContext(), new OnResponseFetched(){
            @Override
            public void onRecipesFetched(List<Recipe> fetchedList){
                recipe = fetchedList;
                recyclerView.setAdapter(mAdapter);
                mAdapter.setRecipe(recipe);
            }
        });

        // Return the root view
        return rootView;
    }

    @Override
    public void onClick(ArrayList<Step> s, ArrayList<Ingredient> i) {
        MainActivity.fragment = new RecipeStuffFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList("ingredients", i);
        b.putParcelableArrayList("steps",s);
        MainActivity.fragment.setArguments(b);
        MainActivity.fragmentManager.beginTransaction()
                .addToBackStack("test")
                .replace(R.id.master_list_fragment, MainActivity.fragment)
                .commit();
    }
}


