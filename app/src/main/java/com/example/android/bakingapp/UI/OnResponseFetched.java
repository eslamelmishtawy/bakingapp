package com.example.android.bakingapp.UI;

import com.example.android.bakingapp.Utility.Recipe;

import java.util.List;

public interface OnResponseFetched {

   void onRecipesFetched(List<Recipe> fetchedList);
}
