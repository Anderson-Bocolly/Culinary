package bocolly.pinheiro.culinary.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import bocolly.pinheiro.culinary.R;
import bocolly.pinheiro.culinary.adapter.RecipeAdapter;
import bocolly.pinheiro.culinary.model.Recipe;

public class SaltyRecipes extends AppCompatActivity {

    private RecyclerView rvRecipes;
    private ArrayList<Recipe> recipes;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salty_recipes);

        init();

        FirebaseApp.initializeApp(SaltyRecipes.this);

        DatabaseReference banco = FirebaseDatabase.getInstance().getReference();

        Query querySaltys = banco.child("recipes").orderByChild("type").equalTo("Salty");
        querySaltys.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                recipes.clear();
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Recipe r = data.getValue(Recipe.class);
                    r.setKey(data.getKey());
                    recipes.add(r);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter.setOnItemClickListener(new RecipeAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Recipe r = recipes.get(position);
                Intent it = new Intent(SaltyRecipes.this, SelectedRecipe.class);
                it.putExtra("r", r);
                startActivity(it);
            }

            @Override
            public void onItemLongClick(View v, int position) {
            }
        }); // fecha adapter.onItemClickListner

    } // fecha onCreate

    private void init(){
        rvRecipes = findViewById(R.id.sr_rv_saltys);

        recipes = new ArrayList<>();
        adapter = new RecipeAdapter(SaltyRecipes.this, recipes);
        rvRecipes.setAdapter(adapter);

        rvRecipes.setHasFixedSize(true);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));
    }

}