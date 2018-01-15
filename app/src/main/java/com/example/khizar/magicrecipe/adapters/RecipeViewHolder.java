package com.example.khizar.magicrecipe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khizar.magicrecipe.R;
import com.example.khizar.magicrecipe.models.Recipe;
import com.example.khizar.magicrecipe.models.Result;
import com.example.khizar.magicrecipe.ui.RecipeDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recipeImageView)
    ImageView mRecipeImageView;
    @BindView(R.id.recipeNameTextView)
    TextView mRecipeNameTextView;
    private Context mContext;
    private Recipe mrecipes;

    public RecipeViewHolder(View itemView, final Recipe recipes) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mrecipes = recipes;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Log.d("parrcel", recipes+"");
                Intent intent = new Intent(mContext, RecipeDetail.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("title",mrecipes.getResults().get(getAdapterPosition()).getTitle());
                intent.putExtra("href",mrecipes.getResults().get(getAdapterPosition()).getHref());
                intent.putExtra("ingredients",mrecipes.getResults().get(getAdapterPosition()).getIngredients());
                intent.putExtra("image",mrecipes.getResults().get(getAdapterPosition()).getThumbnail());
                mContext.startActivity(intent);
            }
        });
    }

    public void bindRecipe(Result recipe) {
        Picasso.with(mContext).load(recipe.getThumbnail()).into(mRecipeImageView);
        mRecipeNameTextView.setText(recipe.getTitle());
    }
}