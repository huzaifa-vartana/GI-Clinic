package com.example.adminpanel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageView1 = findViewById(R.id.dramaView);
        imageView2 = findViewById(R.id.trendingView);
        imageView3 = findViewById(R.id.moviesView);
        imageView4 = findViewById(R.id.slidersView);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
//        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(getApplicationContext(), "Home", 0).show();
                break;
            case R.id.drama:
                Intent browserIntent1 = new Intent(getApplicationContext(), addDrama.class);
                startActivity(browserIntent1);
                Toast.makeText(getApplicationContext(), "Drama", 0).show();

                break;
            case R.id.movies:
                Intent browserIntent2 = new Intent(getApplicationContext(), addMovie.class);
                startActivity(browserIntent2);
                Toast.makeText(getApplicationContext(), "Movies", 0).show();

                break;
            case R.id.sliders:
                Intent browserIntent3 = new Intent(getApplicationContext(), addSlider.class);
                startActivity(browserIntent3);
                Toast.makeText(getApplicationContext(), "Sliders", 0).show();

                break;
            case R.id.trending:
                Intent browserIntent4 = new Intent(getApplicationContext(), addTrending.class);
                startActivity(browserIntent4);
                Toast.makeText(getApplicationContext(), "Trending", 0).show();

                break;
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "Logged Out", 0).show();
                finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HomeActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dramaView) {
            Intent intent = new Intent(getApplicationContext(), addDrama.class);
            startActivity(intent);
        } else if (v.getId() == R.id.moviesView) {
            Intent intent = new Intent(getApplicationContext(), addMovie.class);
            startActivity(intent);
        } else if (v.getId() == R.id.slidersView) {
            Intent intent = new Intent(getApplicationContext(), addSlider.class);
            startActivity(intent);
        } else if (v.getId() == R.id.trendingView) {
            Intent intent = new Intent(getApplicationContext(), addTrending.class);
            startActivity(intent);
        }

    }
}