package com.example.adminpanel;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class PatientCancelApp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "link";
    public static final int ImageBack = 1;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    EditText videoUrl, imageUrl;
    int count;
    boolean aBoolean = false;
    DatabaseReference databaseReference1, databaseReference2;
    Button btn1, btn2;
    Uri uri;
    String i_url;
    StorageReference storageReference;
    boolean isaBoolean = false;
    ImageView imageView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_cancel_appointment);
        drawerLayout = findViewById(R.id.drawerCancel);
        navigationView = findViewById(R.id.navCancel);
        toolbar = findViewById(R.id.toolbarCancel);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cancel Appointment");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        listView = findViewById(R.id.listCancel);
        String[] values = new String[]{"Appointment - 12/31/2020 16:30",
                "Appointment - 12/31/2020 16:30",
                "Appointment - 12/31/2020 16:30",
                "Appointment - 12/31/2020 16:30",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PatientCancelApp.this);
                builder.setMessage("Do you want to cancel the following appointment?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(getApplicationContext(), "Appointment Cancelled", 0).show();
                                finish();
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

        });
    }
//        videoUrl = findViewById(R.id.SliderVideoUrl);
//        btn1 = findViewById(R.id.addSliderBtn);
//        btn2 = findViewById(R.id.trendingImageUrl);
//        videoUrl = findViewById(R.id.SliderVideoUrl);
//        imageView = findViewById(R.id.imageStorage);
//        storageReference = FirebaseStorage.getInstance().getReference().child("SliderImages");
//        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Sliders");
//        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Sliders");
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
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageFromStorage();
//            }
//        });
////        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addTrendingVideo();
//            }
//        });
//    }
//
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
//                                    i_url = String.valueOf(uri);
//                                    isaBoolean = true;
//                                    Picasso.get().load(uri).into(imageView);
//                                    Toast.makeText(getApplicationContext(), "Image Uploaded to Storage", 0).show();
//
//                                }
//                            });
//
//                        }
//                    });
//                }
//
//
//            }
//        }
//    }

//    private void addTrendingVideo() {
//        String vu = videoUrl.getText().toString().trim();
//        String iu = i_url;
//        if (vu.isEmpty() || isaBoolean == false || i_url == "") {
//            Toast.makeText(getApplicationContext(), "Enter Data before uploading", 0).show();
//        } else {
//            if (!vu.contains("https://")) {
//                vu = "https://" + vu;
//            }
//            ModelClassSliders movies = new ModelClassSliders(iu, vu);
//            if (aBoolean) {
//                databaseReference1.child(String.valueOf(count)).setValue(movies);
//                count++;
//                Toast.makeText(getApplicationContext(), "New slider video added", 0).show();
//                videoUrl.getText().clear();
//                i_url = "";
//            }
//        }
//
//    }


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
                Toast.makeText(getApplicationContext(), "Book Appointment", 0).show();

                break;
            case R.id.doctor:
                Intent browserIntent2 = new Intent(getApplicationContext(), PatientDoctorsAvailable.class);
                startActivity(browserIntent2);
                finish();
                Toast.makeText(getApplicationContext(), "Available Doctors", 0).show();

                break;
            case R.id.cancel:
                Intent browserIntent3 = new Intent(getApplicationContext(), PatientCancelApp.class);
                startActivity(browserIntent3);
                finish();
                Toast.makeText(getApplicationContext(), "Cancel Appointment", 0).show();

                break;
            case R.id.updates:
                Intent browserIntent4 = new Intent(getApplicationContext(), PatientUpdates.class);
                startActivity(browserIntent4);
                finish();
                Toast.makeText(getApplicationContext(), "Updates", 0).show();

                break;
            case R.id.support:
                Intent browserIntent5 = new Intent(getApplicationContext(), UserSupport.class);
                startActivity(browserIntent5);
                finish();
                Toast.makeText(getApplicationContext(), "Support", 0).show();


            case R.id.logout:
                Toast.makeText(getApplicationContext(), "Logged Out", 0).show();
                finishAffinity();


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
            Intent intent = new Intent(getApplicationContext(), PatientHome.class);
            startActivity(intent);
            finish();
        }


    }


}