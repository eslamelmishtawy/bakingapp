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
import com.example.android.bakingapp.Utility.LoadRecipe;
import com.example.android.bakingapp.Utility.NoOfCols;
import com.example.android.bakingapp.Utility.Recipe;

import java.util.ArrayList;
import java.util.List;


// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class MasterListFragment extends Fragment implements MasterListAdapter.MasterListAdapterOnClickHandler{
    private List<Recipe> recipe;
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    onClickListener mCallback;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface onClickListener {
        void onCardSelected(String recipee);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (onClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }


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
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_card);
        GridLayoutManager layoutManager
                = new GridLayoutManager(rootView.getContext(), mNoOfColumns, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(MasterListFragment.this);

        // Set the adapter on the GridView
        recyclerView.setAdapter(mAdapter);
        recipe = LoadRecipe.loadRecipe(rootView.getContext());
        mAdapter.setRecipe(recipe);
        // Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked

        // Return the root view
        return rootView;
    }

    @Override
    public void onClick(String recipee) {
        mCallback.onCardSelected(recipee);
    }
}


