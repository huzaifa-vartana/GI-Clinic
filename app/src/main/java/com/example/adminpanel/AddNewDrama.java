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

public class AddNewDrama extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "link";
    public static final int ImageBack = 1;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    int count;
    EditText title, description, videoUrl;
    Button btn1, btn2;
    boolean aBoolean = false;
    DatabaseReference databaseReference1, databaseReference2;
    Uri uri;
    String imageUrl;
    StorageReference storageReference;
    boolean isaBoolean = false;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewdrama);
        drawerLayout = findViewById(R.id.drawerTrending);
        navigationView = findViewById(R.id.navTrending);
        toolbar = findViewById(R.id.toolbarTrending);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Drama");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        title = findViewById(R.id.trendingTitle);
        description = findViewById(R.id.trendingDescription);
        videoUrl = findViewById(R.id.trendingVideoUrl);
        btn2 = findViewById(R.id.trendingImageUrl);
        btn1 = findViewById(R.id.addTrendingBtn);
        imageView = findViewById(R.id.imageStorage);

        storageReference = FirebaseStorage.getInstance().getReference().child("DramaImages");
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Category");
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Category");
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
//          toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageFromStorage();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTrendingVideo();
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
                                    imageUrl = String.valueOf(uri);
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

    private void addTrendingVideo() {
        String titleDrama = title.getText().toString().trim();
        String drama = description.getText().toString().trim();
        String episodes = videoUrl.getText().toString().trim();
        String img = imageUrl;
//            "https://res.cloudinary.com/yaseenys/image/upload/v1589212091/Ozge_Yagiz_mh1578784358331_hgutm6.jpg"
        if (titleDrama.isEmpty() || drama.isEmpty() || episodes.isEmpty() || isaBoolean == false || imageUrl.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter Data before uploading", 0).show();
        } else {
            if (aBoolean) {
                if (!img.contains("https://")) {
                    img = "https://" + img;
                }
                ModelClassNewDrama trending = new ModelClassNewDrama(titleDrama, img, episodes, drama);
                databaseReference1.child(String.valueOf(count)).setValue(trending);
                count++;
                Toast.makeText(getApplicationContext(), "New drama added", 0).show();
                title.getText().clear();
                description.getText().clear();
                videoUrl.getText().clear();
                imageUrl = "";
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), PatientHome.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home", 0).show();
                finish();
                break;
            case R.id.book:
                Intent browserIntent1 = new Intent(getApplicationContext(), BookAppointment.class);
                startActivity(browserIntent1);
                finish();
                Toast.makeText(getApplicationContext(), "Drama", 0).show();

                break;
            case R.id.doctor:
                Intent browserIntent2 = new Intent(getApplicationContext(), PatientDoctorsAvailable.class);
                startActivity(browserIntent2);
                finish();
                Toast.makeText(getApplicationContext(), "Movies", 0).show();

                break;
            case R.id.cancel:
                Intent browserIntent3 = new Intent(getApplicationContext(), PatientCancelApp.class);
                startActivity(browserIntent3);
                finish();
                Toast.makeText(getApplicationContext(), "Sliders", 0).show();

                break;
            case R.id.updates:
                Intent browserIntent4 = new Intent(getApplicationContext(), PatientUpdates.class);
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