package com.example.khizar.magicrecipe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.khizar.magicrecipe.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.findRecipesButton) Button mFindRecipesButton;
    @BindView(R.id.ingredient1EditText) EditText mIngredient1EditText;
    @BindView(R.id.ingredient2EditText) EditText mIngredient2EditText;
    @BindView(R.id.introTextView)TextView mWelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFindRecipesButton.setOnClickListener(this);
        mWelcomeTextView.setText("Welcome,");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findRecipesButton:
                String ingredient1 = mIngredient1EditText.getText().toString();
                String ingredient2 = mIngredient2EditText.getText().toString();
                boolean login = true;
                if (ingredient1.isEmpty()) {
                    mIngredient1EditText.setError("Please enter a item name");
                    login = false;
                }

                if (login) {
                    Intent recipesIntent = new Intent(MainActivity.this, RecipeListActivity.class);
                    recipesIntent.putExtra("ingredient1", ingredient1);
                    recipesIntent.putExtra("ingredient2", ingredient2);
                    startActivity(recipesIntent);
                }

                //Log.d("ingrediients",recipesIntent+"");
                break;
            default:
                break;
        }
    }
}
