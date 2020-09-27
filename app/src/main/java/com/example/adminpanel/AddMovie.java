package com.example.adminpanel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddMovie extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    EditText title, description, videoUrl, imageUrl;
    public static final int ImageBack = 1;
    int count;
    boolean aBoolean = false;
    DatabaseReference databaseReference1, databaseReference2;
    public static final String TAG = "link";
    Button btn1, btn2;
    Uri uri;
    String i_url = "NULL";
    StorageReference storageReference;
    boolean isaBoolean = false;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmovie);
        drawerLayout = findViewById(R.id.drawerMovie);
        navigationView = findViewById(R.id.navMovie);
        toolbar = findViewById(R.id.toolbarMovie);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movies");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        imageView = findViewById(R.id.imageStorage);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        title = findViewById(R.id.trendingTitle);
        description = findViewById(R.id.trendingDescription);
        videoUrl = findViewById(R.id.trendingVideoUrl);
        storageReference = FirebaseStorage.getInstance().getReference().child("MovieImages");
        btn1 = findViewById(R.id.addTrendingBtn);
        btn2 = findViewById(R.id.trendingImageUrl);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Movies");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Movies");
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
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFromStorage();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMovie();
            }
        });
    }

    private void imageFromStorage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ImageBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageBack) {
            if (resultCode == RESULT_OK) {
                uri = data.getData();
                if (uri != null) {
                    final StorageReference imageName = storageReference.child("image" + uri.getLastPathSegment());
                    imageName.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            imageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.d(TAG, String.valueOf(uri));
                                    i_url = String.valueOf(uri);
                                    isaBoolean = true;
                                    Picasso.get().load(uri).into(imageView);

                                    Toast.makeText(getApplicationContext(), "Image Uploaded to Storage", 0).show();
                                }
                            });

                        }
                    });
                }


            }
        }
    }

    private void addMovie() {
        String t = title.getText().toString().trim();
        String d = description.getText().toString().trim();
        String iu = videoUrl.getText().toString().trim();
        String vu = i_url;
        if (t.isEmpty() || d.isEmpty() || iu.isEmpty() || isaBoolean == false || i_url == "") {
            Toast.makeText(getApplicationContext(), "Enter Data before uploading", 0).show();
        } else {
            if (!iu.contains("https://")) {
                iu = "https://" + iu;
            }
            ModelClassVideos trending = new ModelClassVideos(t, d, iu, vu);
            if (aBoolean) {
                databaseReference1.child(String.valueOf(count)).setValue(trending);
                count++;
                Toast.makeText(getApplicationContext(), "New movie added", 0).show();
                title.getText().clear();
                description.getText().clear();
                videoUrl.getText().clear();
                i_url = "";
            }
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