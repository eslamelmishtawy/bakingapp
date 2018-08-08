package com.example.android.bakingapp.Database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "recipe")
public class Entry {
    @NonNull
    @PrimaryKey
    String name;
    String ingredients;

    public Entry(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
