package com.example.android.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;

import java.util.List;

public class RecipeIngredients extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_ingredients);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recipe_ingredient);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        IngredientAdapter mAdapter = new IngredientAdapter();
        recyclerView.setAdapter(mAdapter);
        Intent intent = getIntent();
        mAdapter.setIngredient(intent.<Ingredient>getParcelableArrayListExtra("ing"));
    }
}
