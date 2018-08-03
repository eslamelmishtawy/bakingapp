package com.example.android.bakingapp.UI;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;
import com.example.android.bakingapp.Utility.Recipe;
import com.example.android.bakingapp.Utility.Step;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements MasterListFragment.onClickListener{
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new MasterListFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.master_list_fragment, fragment)
                .commit();
    }

    public void onCardSelected(ArrayList<Step> s, final ArrayList<Ingredient> i) {
        // Create a Toast that displays the position that was clicked
        fragment = new RecipeStuffFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList("ingredients", i);
        b.putParcelableArrayList("steps",s);
        fragment.setArguments(b);
        fragmentManager.beginTransaction()
                .replace(R.id.master_list_fragment, fragment)
                .commit();
    }

    public void onCardSelectedRecipe(int i){
        Toast.makeText(MainActivity.this, "test " + i, Toast.LENGTH_SHORT).show();
    }

}
