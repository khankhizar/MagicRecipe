package com.example.khizar.magicrecipe.services;


import android.util.Log;

import com.example.khizar.magicrecipe.Constants;
import com.example.khizar.magicrecipe.models.Recipe;
import com.example.khizar.magicrecipe.models.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RecipeService {

    String url = Constants.BASE_URL;


    @GET("api/?")
    Call<Recipe> getRecipe(@Query("i") String ingredients, @Query("q") String query, @Query("p") int page);




   /* Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    OkHttpClient client = new OkHttpClient();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//            .callbackExecutor(Executors.newFixedThreadPool(1))
            .build();
*/


}