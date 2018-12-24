package bocolly.pinheiro.culinary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import bocolly.pinheiro.culinary.R;

public class MainActivity extends AppCompatActivity {

    //Drawer
    private Drawer result = null;

    //Toolbar
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Inicio MaterialDrawer

        //Inicio AccountHeader
        //####################### SÓ O CABEÇALHO #######################
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                //download nav
                //https://www.google.com/search?q=nav+header+drawer&rlz=1C1RLNS_pt-BRBR804BR804&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjKwMmUoKnfAhUHDZAKHaJZCksQ_AUIDigB&biw=1366&bih=608#imgrc=zrNwDVP9fD2QfM:
                .withHeaderBackground(R.mipmap.ic_launcher)
                .addProfiles(
                        /*new ProfileDrawerItem().withName("Thiago Cury").withEmail("thiagocury@gmail.com").withIcon(getResources().getDrawable(R.mipmap.ic_launcher)),
                        new ProfileDrawerItem().withName("Joao Silva").withEmail("joaosilva@gmail.com").withIcon(getResources().getDrawable(R.mipmap.ic_launcher))*/
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener(){
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //Inicio Menu
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIdentifier(0).withIcon(GoogleMaterial.Icon.gmd_home),
                        new DividerDrawerItem(),
                        new SectionDrawerItem().withName("Ações"),
                        new SecondaryDrawerItem().withName("Create new recipe").withIdentifier(1),
                        new SecondaryDrawerItem().withName("Sweet recipes").withIdentifier(2),
                        new SecondaryDrawerItem().withName("Salty recipes").withIdentifier(3),
                        new SecondaryDrawerItem().withName("Search").withIdentifier(4)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch ((int)drawerItem.getIdentifier()){
                            case 0:
                                break;
                            case 1:
                                startActivity(new Intent(MainActivity.this,Register.class));
                                break;
                            case 2:
                                startActivity(new Intent(MainActivity.this, SweetRecipes.class));
                                break;
                            case 3:
                                startActivity(new Intent(MainActivity.this, SaltyRecipes.class));
                                break;
                            case 4:
                                startActivity(new Intent(MainActivity.this, Search.class));
                                break;
                        }
                        return false;
                    }
                }).build();


    }//fecha oncreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
