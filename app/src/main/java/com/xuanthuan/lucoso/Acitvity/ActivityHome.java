package com.xuanthuan.lucoso.Acitvity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xuanthuan.lucoso.Object.DataBase;
import com.xuanthuan.lucoso.R;

public class ActivityHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth auth;
    FirebaseUser user;

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    public static DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        init();
        setToolBar();

        createDatabase();
        toolbar.setTitle("Home");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentHome fragmentHome = new FragmentHome();
        transaction.add(R.id.container,fragmentHome);
        transaction.commit();

    }

    private void createDatabase() {
        dataBase = new DataBase(this, "Food.sqlite", null, 1);
    }


    private void setToolBar() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navi, R.string.close_navi);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void init() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        auth.signOut();
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.home:
                toolbar.setTitle("Home");
                fragment = new FragmentHome();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menufood:
                toolbar.setTitle("Menu");
                fragment = new FragmentMenuFood();
                Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case  R.id.message:
                break;
            case R.id.calender_work:
                break;
        }
        transaction.replace(R.id.container,fragment);
        transaction.commit();

        return true;
    }
}