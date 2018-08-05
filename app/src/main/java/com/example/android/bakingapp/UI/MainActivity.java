package com.example.android.bakingapp.UI;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class MainActivity extends AppCompatActivity implements MasterListAdapter.MasterListAdapterOnClickHandler{
    private List<Recipe> recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_master_list);

        int mNoOfColumns = NoOfCols.calculateNoOfColumns(this);
        // Get a reference to the GridView in the fragment_master_list xml layout file
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recipe_card);
        GridLayoutManager layoutManager
                = new GridLayoutManager(this, mNoOfColumns, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final MasterListAdapter mAdapter = new MasterListAdapter(this);
        recipe = LoadRecipe.loadRecipe(this, new OnResponseFetched(){
            @Override
            public void onRecipesFetched(List<Recipe> fetchedList){
                recipe = fetchedList;
                recyclerView.setAdapter(mAdapter);
                mAdapter.setRecipe(recipe);
            }
        });
    }


    @Override
    public void onClick(ArrayList<Step> s, ArrayList<Ingredient> i) {

        Intent intent = new Intent(this, ListActivity.class);
        intent.putParcelableArrayListExtra("steps", s);
        intent.putParcelableArrayListExtra("ingredients", i);
        startActivity(intent);
    }

}
