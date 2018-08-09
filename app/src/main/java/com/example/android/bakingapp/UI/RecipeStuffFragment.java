package com.example.android.bakingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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

public class RecipeStuffFragment extends Fragment implements RecipeAdapter.RecipeAdapterViewHolderOnClickHandler{
    // Define a new interface OnImageClickListener that triggers a callback in the host activity
    public static boolean ingredientBool = false;
    // Mandatory empty constructor
    public RecipeStuffFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_recipe_stuff, container, false);
        if(savedInstanceState == null) {
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_stuff);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setNestedScrollingEnabled(false);
            RecipeAdapter mAdapter = new RecipeAdapter(RecipeStuffFragment.this);
            recyclerView.setAdapter(mAdapter);
            final Bundle b = getArguments();
            mAdapter.setRecipeDescription(b.<Step>getParcelableArrayList("s"));
            // Return the root view
            TextView tv = (TextView) rootView.findViewById(R.id.tv_recipe_ingredients);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ListActivity.mTwoPane){
                        Bundle x = new Bundle();
                        x.putParcelableArrayList("ing", b.<Ingredient>getParcelableArrayList("ingredients"));
                        ListActivity.fragment2 = new RecipeIngredients();
                        ListActivity.fragment2.setArguments(x);
                        ListActivity.fragmentManager2.beginTransaction()
                                .replace(R.id.details_fragment, ListActivity.fragment2)
                                .commit();

                    }else {

                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putParcelableArrayListExtra("ing", b.<Ingredient>getParcelableArrayList("ingredients"));
                        startActivity(intent);
                        ingredientBool = true;
                    }
                }
            });
        }else{
            CardView v = (CardView) rootView.findViewById(R.id.cv_recipe_cardddd);
            v.setVisibility(View.GONE);
        }
        return rootView;
    }

    @Override
    public void onClick(String v, String d) {
        if(ListActivity.mTwoPane){

            Bundle x = new Bundle();
            x.putString("video", v);
            x.putString("description", d);
            ListActivity.fragment2 = new RecipeVideo();
            ListActivity.fragment2.setArguments(x);
            ListActivity.fragmentManager2.beginTransaction()
                    .replace(R.id.details_fragment, ListActivity.fragment2)
                    .commit();

        }else {

            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("video", v);
            intent.putExtra("description", d);
            startActivity(intent);
            ingredientBool = false;
        }
    }


}


