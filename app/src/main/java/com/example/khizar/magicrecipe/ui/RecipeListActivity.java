package com.example.khizar.magicrecipe.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.khizar.magicrecipe.InternetStatus;
import com.example.khizar.magicrecipe.R;
import com.example.khizar.magicrecipe.adapters.RecipeListAdapter;
import com.example.khizar.magicrecipe.models.Recipe;
import com.example.khizar.magicrecipe.services.RecipeService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeListActivity extends BaseActivity{
    public static final String TAG = RecipeListActivity.class.getSimpleName();
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    int page;
    String query;
    int rename_flag = 0;
    RecipeListAdapter adapter;
    private RecipeListAdapter mAdapter;

    public Recipe mRecipes;
    String ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        Intent recipesIntent = getIntent();
        String ingredient1 = recipesIntent.getStringExtra("ingredient1");
        String ingredient2 = recipesIntent.getStringExtra("ingredient2");

        ingredients = (ingredient1 + "," + ingredient2).replaceAll("\\s","");

       if (isOnline())
            getRecipeList();


    }


    private void getRecipeList() {
        try {
            RecipeService recipeService = RecipeService.retrofit.create(RecipeService.class);
            Call<Recipe> call = recipeService.getRecipe(ingredients);
            call.enqueue(new Callback<Recipe>() {
                @Override
                public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                    final Recipe res = response.body();
                    Log.d("response",response.body().toString());
                    RecipeListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new RecipeListAdapter(getApplicationContext(), res, rename_flag);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecipeListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);

                        }
                    });


                }

                public void onFailure(Call<Recipe> call, Throwable t) {
                    Log.d("error", "response : " + t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline() {
        boolean status = InternetStatus.getInstance(getApplicationContext()).isOnline();
        if (status)
            return status;

        else {
            snakbarMessage("Please connect to Internet");
            return status;
        }
    }



    public void snakbarMessage(View view, String message) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.RED);//change Snackbar's background color;
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);//change Snackbar's text color;
        snackbar.show(); // Don’t forget to show!
    }

    public void snakbarMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(getWindow().getDecorView().getRootView(), message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getRecipeList();
                    }
                });
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.rgb(0, 111, 192));//change Snackbar's background color;
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);//change Snackbar's text color;
        snackbar.show(); // Don’t forget to show!
    }



}