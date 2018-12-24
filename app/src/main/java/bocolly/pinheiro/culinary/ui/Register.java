package bocolly.pinheiro.culinary.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import bocolly.pinheiro.culinary.R;
import bocolly.pinheiro.culinary.model.Recipe;

public class Register extends AppCompatActivity {

    private EditText etName;
    private EditText etAuthor;
    private Spinner spType;
    private EditText etIngredients;
    private EditText etPreparation;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        FirebaseApp.initializeApp(Register.this);
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference banco = db.getReference("recipes");

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe r = new Recipe();
                r.setName(etName.getText().toString());
                r.setAuthor(etAuthor.getText().toString());

                if (spType.getSelectedItemPosition() == 0){
                    toast("Select a type!");
                    return;
                }

                r.setType(spType.getSelectedItem().toString());
                r.setIngredients(etIngredients.getText().toString());
                r.setMethodOfPreparation(etPreparation.getText().toString());

                banco.push().setValue(r);



                Intent it = new Intent(Register.this, MainActivity.class);
                startActivity(it);

                toast("Recipe successfully registered!");


            }
        });



    }//fecha onCreate

    public void toast(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void init(){
        etName = findViewById(R.id.re_et_name);
        etAuthor = findViewById(R.id.re_et_author);
        spType = findViewById(R.id.re_sp_type);
        etIngredients = findViewById(R.id.re_et_ingredients);
        etPreparation = findViewById(R.id.re_et_preparation);
        btRegister = findViewById(R.id.re_bt_register);
    }

}
