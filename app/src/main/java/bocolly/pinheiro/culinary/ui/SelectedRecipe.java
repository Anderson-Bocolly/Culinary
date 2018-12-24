package bocolly.pinheiro.culinary.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import bocolly.pinheiro.culinary.R;
import bocolly.pinheiro.culinary.model.Recipe;

public class SelectedRecipe extends AppCompatActivity {

    private TextView tvName;
    private TextView tvAuthor;
    private TextView tvType;
    private TextView tvIngredients;
    private TextView tvPreparation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_recipe);
        init();

        if(getIntent().hasExtra("r")){
            Recipe r = (Recipe)getIntent().getSerializableExtra("r");

            tvName.setText(r.getName());
            tvAuthor.setText(r.getAuthor());
            tvType.setText(r.getType());
            tvIngredients.setText(r.getIngredients());
            tvPreparation.setText(r.getMethodOfPreparation());
        }

    } //fecha onCreate

    public void init(){
        tvName = findViewById(R.id.selec_tv_name);
        tvAuthor = findViewById(R.id.selec_tv_author);
        tvType = findViewById(R.id.selec_tv_type);
        tvIngredients = findViewById(R.id.selec_tv_ingredients);
        tvPreparation = findViewById(R.id.selec_tv_preparation);
    }

}
