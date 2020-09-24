package com.example.adminpanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSlider extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    EditText videoUrl, imageUrl;
    Button btn;
    int count;
    boolean aBoolean = false;
    DatabaseReference databaseReference1, databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addslider);
        drawerLayout = findViewById(R.id.drawerSlider);
        navigationView = findViewById(R.id.navSlider);
        toolbar = findViewById(R.id.toolbarSlider);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sliders");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        videoUrl = findViewById(R.id.SliderVideoUrl);
        imageUrl = findViewById(R.id.SliderImageUrl);
        btn = findViewById(R.id.addSliderBtn);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Sliders");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Sliders");
        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    count = Integer.parseInt(dataSnapshot1.getKey());
                }
                count++;
                aBoolean = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTrendingVideo();
            }
        });
    }

    private void addTrendingVideo() {
        String vu = imageUrl.getText().toString().trim();
        String iu = videoUrl.getText().toString().trim();
        ModelClassSliders movies = new ModelClassSliders(iu, vu);
        if (aBoolean) {
            databaseReference1.child(String.valueOf(count)).setValue(movies);
            count++;

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home", 0).show();
                finish();
                break;
            case R.id.drama:
                Intent browserIntent1 = new Intent(getApplicationContext(), AddDrama.class);
                startActivity(browserIntent1);
                finish();
                Toast.makeText(getApplicationContext(), "Drama", 0).show();

                break;
            case R.id.movies:
                Intent browserIntent2 = new Intent(getApplicationContext(), AddMovie.class);
                startActivity(browserIntent2);
                finish();
                Toast.makeText(getApplicationContext(), "Movies", 0).show();

                break;
            case R.id.sliders:
                Intent browserIntent3 = new Intent(getApplicationContext(), AddSlider.class);
                startActivity(browserIntent3);
                finish();
                Toast.makeText(getApplicationContext(), "Sliders", 0).show();

                break;
            case R.id.trending:
                Intent browserIntent4 = new Intent(getApplicationContext(), AddTrending.class);
                startActivity(browserIntent4);
                finish();
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
            super.onBackPressed();
        }


    }

}