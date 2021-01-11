package com.example.adminpanel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class AdminUpdates extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_updates);
        drawerLayout = findViewById(R.id.drawerUpdates);
        navigationView = findViewById(R.id.navUpdates);
        toolbar = findViewById(R.id.toolbarUpdates);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Updates");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        listView = findViewById(R.id.listUpdates);
        String[] values = new String[]{"Appointment Confirmed by Dr. Huzaifa",
                "Appointment Cancelled by Subhash 2017192",
                "Dr. Khimji wrote a prescription for Huzaifa 2017192"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // ListView Item Click Listener
//        storageReference = FirebaseStorage.getInstance().getReference().child("TrendingImages");
//        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Trending");
//        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Trending");
//        databaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//                    count = Integer.parseInt(dataSnapshot1.getKey());
//                }
//                count++;
//                aBoolean = true;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
////          toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageFromStorage();
//            }
//        });
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addTrendingVideo();
//            }
//        });
//    }

//    private void imageFromStorage() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, ImageBack);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ImageBack) {
//            if (resultCode == RESULT_OK) {
//                uri = data.getData();
//                if (uri != null) {
//                    final StorageReference imageName = storageReference.child("image" + uri.getLastPathSegment());
//                    imageName.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
//                            imageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//                                    Log.d(TAG, String.valueOf(uri));
//                                    imageUrl = String.valueOf(uri);
//                                    isaBoolean = true;
//                                    Picasso.get().load(uri).into(imageView);
//                                    Toast.makeText(getApplicationContext(), "Image Uploaded to Storage", 0).show();
//                                }
//                            });
//                        }
//                    });
//                }
//            }
//        }
//    }
//
//    private void addTrendingVideo() {
//        String t = title.getText().toString().trim();
//        String d = description.getText().toString().trim();
//        String iu = videoUrl.getText().toString().trim();
//        String vu = imageUrl;
////            "https://res.cloudinary.com/yaseenys/image/upload/v1589212091/Ozge_Yagiz_mh1578784358331_hgutm6.jpg"
//        if (t.isEmpty() || d.isEmpty() || iu.isEmpty() || isaBoolean == false || imageUrl.equals("")) {
//            Toast.makeText(getApplicationContext(), "Enter Data before uploading", 0).show();
//        } else {
//            if (aBoolean) {
//                if (!iu.contains("https://")) {
//                    iu = "https://" + iu;
//                }
//                ModelClassVideos trending = new ModelClassVideos(t, d, iu, vu);
//                databaseReference1.child(String.valueOf(count)).setValue(trending);
//                count++;
//                Toast.makeText(getApplicationContext(), "New trending video added", 0).show();
//                title.getText().clear();
//                description.getText().clear();
//                videoUrl.getText().clear();
//                imageUrl = "";
//            }
//        }
//    }


//
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home", 0).show();
                finish();
                break;
            case R.id.book:
                Intent browserIntent1 = new Intent(getApplicationContext(), ConfirmAppointment.class);
                startActivity(browserIntent1);
                finish();
                Toast.makeText(getApplicationContext(), "Confirm Appointment", 0).show();

                break;
            case R.id.doctor:
                Intent browserIntent2 = new Intent(getApplicationContext(), AdminDoctorAvailable.class);
                startActivity(browserIntent2);
                finish();
                Toast.makeText(getApplicationContext(), "Available Doctors", 0).show();

                break;
            case R.id.cancel:
                Intent browserIntent3 = new Intent(getApplicationContext(), AdminCancelApp.class);
                startActivity(browserIntent3);
                finish();
                Toast.makeText(getApplicationContext(), "Cancel Appointment", 0).show();

                break;
            case R.id.updates:
                Intent browserIntent4 = new Intent(getApplicationContext(), AdminUpdates.class);
                startActivity(browserIntent4);
                finish();
                Toast.makeText(getApplicationContext(), "Updates", 0).show();

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
            Intent intent = new Intent(getApplicationContext(), AdminHome.class);
            startActivity(intent);
            finish();
        }


    }


}