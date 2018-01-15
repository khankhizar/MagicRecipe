package com.example.khizar.magicrecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khizar.magicrecipe.R;
import com.example.khizar.magicrecipe.models.Recipe;
import com.example.khizar.magicrecipe.models.Result;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private Context context;
    private Recipe recipes;
    int rename_flag;
    private boolean isLoadingAdded = false;

    public RecipeListAdapter(Context context, Recipe recipes, int rename_flag) {
        try {
            this.context = context;
            this.recipes = recipes;
            this.rename_flag = this.rename_flag;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view, recipes);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bindRecipe(recipes.getResults().get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.getResults().size();
    }

}






