package com.example.khizar.magicrecipe.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khizar.magicrecipe.R;
import com.example.khizar.magicrecipe.models.Recipe;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetail extends AppCompatActivity  {
    @BindView(R.id.recipeImageView)
    ImageView mImageLabel;
    @BindView(R.id.recipeNameTextView)
    TextView mNameLabel;
    @BindView(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @BindView(R.id.instructionsTextView)
    TextView ingredient;
    private Context mContext;
    private Recipe mRecipe;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);
        ButterKnife.bind(this);
        Intent i = getIntent();
        i.getStringExtra("title");
        i.getStringExtra("href");
        i.getStringExtra("ingredients");
        i.getStringExtra("image");
        Log.d("title", i.getStringExtra("title"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        mNameLabel.setText(i.getStringExtra("title"));
        mWebsiteLabel.setText(i.getStringExtra("href"));
        ingredient.setText(i.getStringExtra("ingredients"));
        url = i.getStringExtra("ingredients");
        Picasso.with(mContext).load(i.getStringExtra("image")).into(mImageLabel);
    }

}