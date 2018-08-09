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
    public static Fragment fragment2;
    public static FragmentManager fragmentManager2;
    public static boolean mTwoPane;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle b = new Bundle();
        fragmentManager2 = getSupportFragmentManager();
        b.putParcelableArrayList("ingredients", intent.getParcelableArrayListExtra("ingredients"));
        b.putParcelableArrayList("s",intent.<Step>getParcelableArrayListExtra("steps"));

        if(findViewById(R.id.details_fragment)!=null){
            mTwoPane = true;
        }else{
            mTwoPane = false;
        }


        fragment = new RecipeStuffFragment();
        fragment.setArguments(b);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.master_list_fragment, fragment)
                .commit();


    }
}
