package com.thedancercodes.recipe.app.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thedancercodes.recipe.app.R;

public class RecipesActivity extends AppCompatActivity
{
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        setupRecyclerView();
    }

    @Override
    protected void onResume ()
    {
        super.onResume();
    }

    @Override
    protected void onPause ()
    {
        super.onPause();
    }

    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter( this );
        recipesRecyclerView.setAdapter( adapter );
    }

}
