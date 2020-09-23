package com.example.adminpanel;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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

public class addTrending extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    EditText title, description, videoUrl, imageUrl;
    Button btn;
    int count;
    boolean aBoolean = false;
    DatabaseReference databaseReference1, databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtrending);
        drawerLayout = findViewById(R.id.drawerTrending);
        navigationView = findViewById(R.id.navTrending);
        toolbar = findViewById(R.id.toolbarTrending);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trending");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        title = findViewById(R.id.trendingTitle);
        description = findViewById(R.id.trendingDescription);
        videoUrl = findViewById(R.id.trendingVideoUrl);
        imageUrl = findViewById(R.id.trendingImageUrl);
        btn = findViewById(R.id.addTrendingBtn);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Trending");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Trending");
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
        videoUrl.setText("https:// - Video Url");
        Selection.setSelection(videoUrl.getText(), videoUrl.getText().length());


        videoUrl.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("https://")) {

                }


            }
        });
        imageUrl.setText("https:// - Image Url");
        Selection.setSelection(imageUrl.getText(), imageUrl.getText().length());


        imageUrl.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("https://")) {

                }


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
        String t = title.getText().toString().trim();
        String d = description.getText().toString().trim();
        String vu = imageUrl.getText().toString().trim();
        String iu = videoUrl.getText().toString().trim();
        treningVideos trending = new treningVideos(t, d, vu, iu);
        if (aBoolean) {
            databaseReference1.child(String.valueOf(count)).setValue(trending);
            count++;

        }
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
                            addTrending.this.finish();
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

}