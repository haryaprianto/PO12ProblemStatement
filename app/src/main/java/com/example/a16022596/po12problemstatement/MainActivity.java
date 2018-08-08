package com.example.a16022596.po12problemstatement;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    MenuAdapter ma;
    ArrayList<MenuClass> alNames;
    String currentTitle;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTitle = this.getTitle().toString();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);


        ab = getSupportActionBar();

        alNames = new ArrayList<MenuClass>();
        alNames.add(new MenuClass("Bio"));
        alNames.add(new MenuClass("Vaccination"));
        alNames.add(new MenuClass("Anniversary"));
        alNames.add(new MenuClass("About Us"));

        ma = new MenuAdapter(this, R.layout.row, alNames);

        drawerList.setAdapter(ma);

        drawerList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int
                    position, long arg3) {

                Fragment fragment = null;
                if (position == 0)
                    fragment = new BioFragment();

                else if (position == 1)
                    fragment = new VaccinationFragment();
                else if (position == 2)
                    fragment = new AnniversaryFragment();
                else {
                    fragment = new AboutFragment();
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                trans.replace(R.id.content_frame, fragment);
                trans.commit();
                drawerList.setItemChecked(position, true);
                MenuClass selectedName = alNames.get(position);
                currentTitle = selectedName.getTitle();
                ab.setTitle(currentTitle);
                drawerLayout.closeDrawer(drawerList);
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,      /* DrawerLayout object */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Would be called when a drawer has completely closed */
            @Override
            public void onDrawerClosed(android.view.View view) {
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            /** Would be called when a drawer has completely open */
            @Override
            public void onDrawerOpened(android.view.View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}

