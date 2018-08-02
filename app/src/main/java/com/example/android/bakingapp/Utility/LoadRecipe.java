package com.example.android.bakingapp.Utility;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.bakingapp.UI.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadRecipe {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static List<Recipe> recipe;
    public static List<Recipe> loadRecipe(final Context context) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        GetData client = retrofit.create(GetData.class);
        Call<List<Recipe>> call = client.recipeCardName();
        call.enqueue(new Callback<List<Recipe>>() {


            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                recipe = response.body();
                recipe.get(0).getName();
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "error :(", Toast.LENGTH_SHORT).show();
            }
        });
        return recipe;
    }
}
