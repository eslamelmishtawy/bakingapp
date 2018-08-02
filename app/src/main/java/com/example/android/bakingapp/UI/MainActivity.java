package com.example.android.bakingapp.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android.bakingapp.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.onClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCardSelected(String recipee) {
        // Create a Toast that displays the position that was clicked
        Toast.makeText(this, "Position clicked = " + recipee, Toast.LENGTH_SHORT).show();

    }

}
