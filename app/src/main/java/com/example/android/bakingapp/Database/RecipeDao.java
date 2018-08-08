package com.example.android.bakingapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.bakingapp.Utility.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe")
    LiveData<List<Entry>> loadAllRecipe();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(Entry entry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Entry entry);

    @Query("SELECT name FROM recipe")
    LiveData<List<String>> getRecipeNames();

    @Query("SELECT ingredients FROM recipe")
    LiveData<List<String>> getRecipeIngredients();

}