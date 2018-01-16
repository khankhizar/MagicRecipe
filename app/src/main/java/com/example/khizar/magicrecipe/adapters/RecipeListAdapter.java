package com.example.khizar.magicrecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khizar.magicrecipe.R;
import com.example.khizar.magicrecipe.models.Recipe;
import com.example.khizar.magicrecipe.models.Result;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private Context context;
    private Recipe recipes;
    int rename_flag;
    private boolean isLoadingAdded = false;
    private List<Result> movieResults;

   // public RecipeListAdapter(Context context, Recipe recipes, int rename_flag) {
   public RecipeListAdapter(Context context) {
        try {
            this.context = context;
            this.recipes = recipes;
            this.rename_flag = this.rename_flag;
            movieResults = new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);
        //RecipeViewHolder viewHolder = new RecipeViewHolder(view, recipes);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view, movieResults);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        //holder.bindRecipe(recipes.getResults().get(position));
        holder.bindRecipe(movieResults.get(position));
    }

    @Override
    public int getItemCount() {
        //return recipes.getResults().size();
        return movieResults.size();
    }

    public void add(Result r) {
        movieResults.add(r);
        notifyItemInserted(movieResults.size() - 1);
    }

    public void addAll(List<Result> moveResults) {
        for (Result result : moveResults) {
            add(result);
        }
    }
    public void remove(Result r) {
        int position = movieResults.indexOf(r);
        if (position > -1) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }


    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Result());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieResults.size() - 1;
        Result result = getItem(position);

        if (result != null) {
            movieResults.remove(position);
            notifyItemRemoved(position);
        }
    }
    public Result getItem(int position) {
        return movieResults.get(position);
    }
}






