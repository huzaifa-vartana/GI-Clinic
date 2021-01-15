package com.example.adminpanel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.android.material.navigation.NavigationView;

public class PatientHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patienthome);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        imageView1 = findViewById(R.id.bookView);
        imageView2 = findViewById(R.id.doctorView);
        toolbar.setNavigationIcon(R.drawable.newmenu);
        imageView3 = findViewById(R.id.cancelView);
        imageView4 = findViewById(R.id.updatesView);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5 = findViewById(R.id.ambulance);
        imageView5.setOnClickListener(this);
        toolbar = findViewById(R.id.toolbar);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
//        toggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        // Create Alert using Builder
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean dialogShown = settings.getBoolean("dialogShown", false);

        if (!dialogShown) {
            // AlertDialog code here
            CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this)
                    .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                    .setTitle("New User Alert")
                    .setMessage("Looks like you're a new user. Watch this tutorial to get started")
                    .addButton("Watch Tutorial", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(PatientHome.this.getApplicationContext(), "Loading video", 0).show();
                            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                            startActivity(launchIntent);
                            dialog.dismiss();
                        }
                    })
                    .addButton("I am a Pro", -1, -1, CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

// Show the alert
            builder.show();

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("dialogShown", true);
            editor.commit();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finishAffinity();
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
        if (v.getId() == R.id.bookView) {
            Intent intent = new Intent(getApplicationContext(), BookAppointment.class);
            startActivity(intent);
        } else if (v.getId() == R.id.doctorView) {
            Intent intent = new Intent(getApplicationContext(), PatientDoctorsAvailable.class);
            startActivity(intent);
        } else if (v.getId() == R.id.cancelView) {
            Intent intent = new Intent(getApplicationContext(), PatientCancelApp.class);
            startActivity(intent);
        } else if (v.getId() == R.id.updatesView) {
            Intent intent = new Intent(getApplicationContext(), PatientUpdates.class);
            startActivity(intent);
        } else if (v.getId() == R.id.ambulance) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:03004416110"));
            startActivity(intent);
        }

    }
}