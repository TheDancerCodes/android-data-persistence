package com.thedancercodes.recipe.app.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thedancercodes.recipe.app.R;
import com.thedancercodes.recipe.app.db.RecipeAppDataSource;
import com.thedancercodes.recipe.app.db.RecipesDataProvider;
import com.thedancercodes.recipe.app.models.Recipe;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecipesActivity extends AppCompatActivity {
    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;
    private RecipeAppDataSource dataSource;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        // Create a new instance of the RecipeAppDataSource class.
        dataSource = new RecipeAppDataSource(this);

        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();


        disposable = dataSource.getAllRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Recipe>>() {
                    @Override
                    public void accept(List<Recipe> recipes) throws Exception {
                        adapter.setRecipes(recipes);
                        adapter.notifyDataSetChanged();
                    }
                });

        // Take advantage of RxJava’s Completable class to do background work
        Completable.fromCallable(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                // Iterate over our list of recipes from the RecipesDataProvider &
                // then pass them one by one to the DataSources’s createRecipe() method.
                for (Recipe recipe : RecipesDataProvider.recipesList) {

                    // Call through to our createRecipe method on the dataSource class.
                    dataSource.createRecipe(recipe);
                }

                // Return null as we won't be doing anything with the results right now.
                return null;
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {

        // Dispose our Disposable
        disposable.dispose();
        super.onDestroy();
    }
}
