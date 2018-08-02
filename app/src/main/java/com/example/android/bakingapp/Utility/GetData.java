package com.example.android.bakingapp.Utility;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetData {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> recipeCardName();
}
