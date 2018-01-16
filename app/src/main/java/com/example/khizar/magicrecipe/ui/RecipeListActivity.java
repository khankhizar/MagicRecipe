package com.example.khizar.magicrecipe.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.khizar.magicrecipe.InternetStatus;
import com.example.khizar.magicrecipe.PaginationScollListener;
import com.example.khizar.magicrecipe.R;
import com.example.khizar.magicrecipe.adapters.RecipeListAdapter;
import com.example.khizar.magicrecipe.models.Recipe;
import com.example.khizar.magicrecipe.models.Result;
import com.example.khizar.magicrecipe.services.ReciipeApi;
import com.example.khizar.magicrecipe.services.RecipeService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeListActivity extends BaseActivity{
    public static final String TAG = RecipeListActivity.class.getSimpleName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    int page;
    String query;
    int rename_flag = 0;
    RecipeListAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    public Recipe mRecipes;
    String ingredients;

    private static final int PAGE_START = 2;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 100; //your total page
    private int currentPage = PAGE_START;
    private RecipeService recipeService;
    String ingredient1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        Intent recipesIntent = getIntent();
        ingredient1 = recipesIntent.getStringExtra("ingredient1");
        String ingredient2 = recipesIntent.getStringExtra("ingredient2");

        ingredients = (ingredient1 + "," + ingredient2).replaceAll("\\s", "");
        Log.d("ingredients",ingredients);

        adapter = new RecipeListAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new PaginationScollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                // mocking network delay for API call
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                }, 1000);

            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        //init service and load data
        recipeService = ReciipeApi.getClient().create(RecipeService.class);

        loadFirstPage();


        //  loadFirstPage();

    }

    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");

        callTopRatedMoviesApi().enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                // Got data. Send it to adapter

                try {
                    List<Result> results = fetchResults(response);
                    //progressBar.setVisibility(View.GONE);
                    adapter.addAll(results);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (currentPage <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });

    }

    /**
     * @param response extracts List<{@link Result>} from response
     * @return
     */
    private List<Result> fetchResults(Response<Recipe> response) {
        Recipe topRatedMovies = response.body();
        return topRatedMovies.getResults();
    }

    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + currentPage);

        callTopRatedMoviesApi().enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                try {
                    List<Result> results = fetchResults(response);
                    adapter.addAll(results);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (currentPage != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                t.printStackTrace();
                // TODO: 08/11/16 handle failure
            }
        });
    }
    /**
     * Performs a Retrofit call to the top rated movies API.
     * Same API call for Pagination.
     * As {@link #currentPage} will be incremented automatically
     * by @{@link PaginationScollListener} to load next page.
     */
    private Call<Recipe> callTopRatedMoviesApi() {
        Log.d("ing",ingredient1);
        Log.d("page", String.valueOf(currentPage));
        return recipeService.getRecipe(
                ingredients,
                query,
                currentPage

        );

    }


}