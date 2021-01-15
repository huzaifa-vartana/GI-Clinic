package com.example.adminpanel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookAppointment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    public static final String TAG = "link";
    public static final int ImageBack = 1;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    int count;
    private static final String[] paths = {"9.00", "9.30", "10.00", "16.00"};
    EditText date_time_in;
    Button b1;
    EditText e1, e2, e3, e6;
    AutoCompleteTextView e4, e5;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);
        drawerLayout = findViewById(R.id.drawerDrama);
        navigationView = findViewById(R.id.navDrama);
        toolbar = findViewById(R.id.toolbarDrama);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Book Appointment");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        date_time_in = findViewById(R.id.patientBookingDateTime);
        date_time_in.setInputType(InputType.TYPE_NULL);
        date_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_time_in);
            }
        });
        e1 = findViewById(R.id.patientName);
        e2 = findViewById(R.id.patientRegNumber);
        e3 = findViewById(R.id.patientAge);
        e4 = findViewById(R.id.patientFaculty);
        e5 = findViewById(R.id.patientNameQualification);
        e6 = findViewById(R.id.patientBookingDateTime);
        b1 = findViewById(R.id.bookAppointmentBtn);
        spinner = findViewById(R.id.patientBookSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(BookAppointment.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        String[] countries = {"FCS", "FES", "FME", "FCE", "FCVE", "FEEE", "FEEP", "FMCE"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        e4.setAdapter(adapter1);
        String[] qualification = {"Bachelors", "Masters", "Phd", "Teaching Faculty"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, qualification);
        e5.setAdapter(adapter2);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookApp();
            }
        });

    }

    private void bookApp() {

        String name = e1.getText().toString().trim();
        String age = e2.getText().toString().trim();
        String faculty = e3.getText().toString().trim();
        String reg = e4.getText().toString().trim();
        String datetime = e5.getText().toString().trim();
        String qualification = e6.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(getApplicationContext(), "Enter data!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(faculty)) {
            Toast.makeText(getApplicationContext(), "Enter data!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(reg)) {
            Toast.makeText(getApplicationContext(), "Enter data!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(qualification)) {
            Toast.makeText(getApplicationContext(), "Enter data!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(datetime)) {
            Toast.makeText(getApplicationContext(), "Enter data!!", Toast.LENGTH_SHORT).show();
            return;
        }
        e1.getText().clear();
        e2.getText().clear();
        e3.getText().clear();
        e4.getText().clear();
        e5.getText().clear();
        e6.getText().clear();

        Toast.makeText(getApplicationContext(), "Appointment Booked. Wait for approval", Toast.LENGTH_SHORT).show();


    }

    private void showDateDialog(final EditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                date_time_in.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };

        new DatePickerDialog(BookAppointment.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), PatientHome.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}