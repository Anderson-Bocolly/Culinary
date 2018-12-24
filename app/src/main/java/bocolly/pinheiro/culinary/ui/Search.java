package bocolly.pinheiro.culinary.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class Search extends AppCompatActivity {

    private EditText etName;
    private Button btSearch;
    private RecyclerView rvRecipes;
    private ArrayList<Recipe> recipes;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();



        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseApp.initializeApp(Search.this);
                DatabaseReference banco = FirebaseDatabase.getInstance().getReference();

                Query querySearch = banco.child("recipes").orderByChild("name").startAt(etName.getText().toString()).endAt(etName.getText().toString()+"\uf8ff");
                querySearch.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        recipes.clear();
                        for (DataSnapshot data: dataSnapshot.getChildren()){
                            Recipe r = data.getValue(Recipe.class);
                            r.setKey(data.getKey());
                            recipes.add(r);
                        }
                        adapter.notifyDataSetChanged();

                        if (recipes.isEmpty()){
                            toast("No recipe found!");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        }); // fecha OnClickListener

        adapter.setOnItemClickListener(new RecipeAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Recipe r = recipes.get(position);
                Intent it = new Intent(Search.this, SelectedRecipe.class);
                it.putExtra("r", r);
                startActivity(it);
            }

            @Override
            public void onItemLongClick(View v, int position) {
            }
        }); // fecha adapter.onItemClickListner


    } // fecha onCreate

    public void toast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void init(){
        etName = findViewById(R.id.search_et_name);
        btSearch = findViewById(R.id.search_bt_search);
        rvRecipes = findViewById(R.id.search_rv_recipes);

        recipes = new ArrayList<>();
        adapter = new RecipeAdapter(Search.this, recipes);
        rvRecipes.setAdapter(adapter);

        rvRecipes.setHasFixedSize(true);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));
    }

}
