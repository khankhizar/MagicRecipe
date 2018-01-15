package com.example.khizar.magicrecipe;

import android.content.Intent;
import android.widget.TextView;

import com.example.khizar.magicrecipe.ui.MainActivity;
import com.example.khizar.magicrecipe.ui.RecipeListActivity;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView introTextView = (TextView) activity.findViewById(R.id.introTextView);
        assertTrue(("Find recipes using what you have in your kitchen").equals(introTextView.getText().toString()));
    }

    @Test
    public void secondActivityStarted() {
        activity.findViewById(R.id.findRecipesButton).performClick();
        Intent expectedIntent = new Intent(activity, RecipeListActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}