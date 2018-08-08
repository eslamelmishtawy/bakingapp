package com.example.android.bakingapp.UI;

import android.arch.lifecycle.LiveData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.AppExecutors;
import com.example.android.bakingapp.Database.AppDatabase;
import com.example.android.bakingapp.Database.Entry;
import com.example.android.bakingapp.Provider.RecipeContract;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;
import com.example.android.bakingapp.Utility.LoadRecipe;
import com.example.android.bakingapp.Utility.NoOfCols;
import com.example.android.bakingapp.Utility.Recipe;
import com.example.android.bakingapp.Utility.Step;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.bakingapp.Provider.RecipeContract.RecipeEntry.COLUMN_RECIPE_INGREDIENTS;
import static com.example.android.bakingapp.Provider.RecipeContract.RecipeEntry.COLUMN_REIPE_NAMES;
import static com.example.android.bakingapp.Provider.RecipeContract.RecipeEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements MasterListAdapter.MasterListAdapterOnClickHandler{
    private List<Recipe> recipe;
    private AppDatabase mDb;
    private boolean databaseCheck = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_master_list);
        mDb = AppDatabase.getInstance(getApplicationContext());
        int mNoOfColumns = NoOfCols.calculateNoOfColumns(this);
        // Get a reference to the GridView in the fragment_master_list xml layout file
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_recipe_card);
        GridLayoutManager layoutManager
                = new GridLayoutManager(this, mNoOfColumns, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        final MasterListAdapter mAdapter = new MasterListAdapter(this);
            recipe = LoadRecipe.loadRecipe(this, new OnResponseFetched() {
                @Override
                public void onRecipesFetched(List<Recipe> fetchedList) {
                    recipe = fetchedList;
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setRecipe(recipe);
                    String ing = "";


                    for (int i = 0; i < recipe.size(); i++) {
                        ing = "";
                        ContentValues contentValues = new ContentValues();
                        String [] requestedColumns = {
                                COLUMN_REIPE_NAMES,
                        };
                        Uri uri = RecipeContract.RecipeEntry.CONTENT_URI;
                        Cursor mCursor = MainActivity.this.getContentResolver().query(uri,
                                requestedColumns,
                                RecipeContract.RecipeEntry.COLUMN_REIPE_NAMES + "='" + recipe.get(i).getName() + "'",
                                null, null);

                        int duplicate = mCursor.getCount(); // If there is a row containing the searched name then the count of the cursor will not be 0.

                        if(duplicate !=0)
                        {
                            break;
                        }
                        else
                        {
                            contentValues.put(RecipeContract.RecipeEntry.COLUMN_REIPE_NAMES, recipe.get(i).getName());
                        }

                        for (int j = 0; j < recipe.get(i).getIngredients().size(); j++) {
                            ing += Double.toString(recipe.get(i).getIngredients().get(j).getQuantity())
                                    + " " + recipe.get(i).getIngredients().get(j).getMeasure() + " " +
                                    recipe.get(i).getIngredients().get(j).getIngredient() + ",";
                            contentValues.put(RecipeContract.RecipeEntry.COLUMN_RECIPE_INGREDIENTS, ing);
                        }
                        getContentResolver().insert(RecipeContract.RecipeEntry.CONTENT_URI, contentValues);
                    }
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
