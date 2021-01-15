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
        final String[] values = new String[]{"Appointment Confirmed by Dr. Huzaifa",
                "Appointment Cancelled by Subhash 2017192",
                "Dr. Khimji wrote a prescription for Huzaifa 2017192"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent browserIntent3 = new Intent(getApplicationContext(), AddNewEpisode.class);
//                browserIntent3.putExtra("dramaName", mNames.get(position));
//                startActivity(browserIntent3);
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(AdminUpdates.this);
                builderSingle.setIcon(R.drawable.notification);
                builderSingle.setTitle("Updates");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdminUpdates.this, android.R.layout.select_dialog_item);
                arrayAdapter.add(values[position]);
                arrayAdapter.add("Dummy data added");
//                arrayAdapter.add("Reg #: 2017192");
//                arrayAdapter.add("Faculty: FCS");
//                arrayAdapter.add("Last Appointment: 25 December, 2020 - 16.00");
//                arrayAdapter.add("Regular Doctor: Dr Subash");

                builderSingle.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(AdminUpdates.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Full Details");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();
//                Toast.makeText(getApplicationContext(), episodeNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });
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