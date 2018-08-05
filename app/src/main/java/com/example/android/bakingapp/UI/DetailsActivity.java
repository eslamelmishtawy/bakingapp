package com.example.android.bakingapp.UI;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;

public class DetailsActivity extends AppCompatActivity {

    public static Fragment fragment2;
    public static FragmentManager fragmentManager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle b = new Bundle();
        if(RecipeStuffFragment.ingredientBool) {
            Intent intent = getIntent();
            b.putParcelableArrayList("ing", intent.<Ingredient>getParcelableArrayListExtra("ing"));
            fragment2 = new RecipeIngredients();
            fragment2.setArguments(b);
            fragmentManager2 = getSupportFragmentManager();
            fragmentManager2.beginTransaction()
                    .add(R.id.details_fragment, fragment2)
                    .commit();
        }else{
            Intent intent = getIntent();
            String videoURL = intent.getStringExtra("video");
            String desc = intent.getStringExtra("description");
            b.putString("video", videoURL);
            b.putString("des", desc);
            fragment2 = new RecipeVideo();
            fragment2.setArguments(b);
            fragmentManager2 = getSupportFragmentManager();
            fragmentManager2.beginTransaction()
                    .replace(R.id.details_fragment, fragment2)
                    .commit();
        }
    }

}
