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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
