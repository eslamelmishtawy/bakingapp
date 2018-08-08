package com.example.android.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.Utility.Ingredient;
import com.example.android.bakingapp.Utility.Step;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static Fragment fragment;
    public static FragmentManager fragmentManager;
    ArrayList<Step> s;
    public static Fragment fragment2;
    public static FragmentManager fragmentManager2;
    private boolean mTwoPane;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.details_fragment) != null){
            mTwoPane = true;

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


        }else{
            mTwoPane = false;
        }

        Intent intent = getIntent();
        s = intent.getParcelableArrayListExtra("steps");
        Bundle b = new Bundle();
        b.putParcelableArrayList("ingredients", intent.getParcelableArrayListExtra("ingredients"));
        b.putParcelableArrayList("s",intent.<Step>getParcelableArrayListExtra("steps"));

        fragment = new RecipeStuffFragment();
        fragment.setArguments(b);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.master_list_fragment, fragment)
                .commit();



    }
}
