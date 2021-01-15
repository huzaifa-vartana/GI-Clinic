package com.example.adminpanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminDoctorAvailable extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "dramaname" +
            "";
    public static final int ImageBack = 1;
    private final ArrayList<String> doctorNames = new ArrayList<>();
    private final ArrayList<String> availableStatuses = new ArrayList<>();
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    int count;
    EditText title, description, videoUrl;
    Button btn1, btn2;
    boolean aBoolean = false;
    DatabaseReference databaseReference1, databaseReference2;
    Uri uri;
    ListView listView;
    String name;
    String descp;
    int img_url;
    String video_url;
    String dramaName;
    StorageReference storageReference;
    boolean isaBoolean = false;
    String[] item;
    int[] img;
    String[] vid;
    String[] d2;
    ExtendedFloatingActionButton extendedFloatingActionButton;
    String doctorName1, doctorName2, doctorName3, doctorName4;
    String availableStatus1, availableStatus2, availableStatus3, availableStatus4;
    String[] doctorArray;
    String[] availableArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor_available);
        listView = findViewById(R.id.listDoctor);
        drawerLayout = findViewById(R.id.drawerDoctor);
        navigationView = findViewById(R.id.navDoctor);
        toolbar = findViewById(R.id.toolbarDoctor);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Doctors");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        doctorName1 = "Dr. Huzaifa";
        doctorName2 = "Dr. Khimji";
        doctorName3 = "Dr. Bajwa";
        doctorName4 = "Dr. Subhash";
        availableStatus1 = "Available";
        availableStatus2 = "Unavailable";
        availableStatus3 = "Unavailable";
        availableStatus4 = "Available";
        doctorNames.add(doctorName1);
        doctorNames.add(doctorName2);
        doctorNames.add(doctorName3);
        doctorNames.add(doctorName4);
        availableStatuses.add(availableStatus1);
        availableStatuses.add(availableStatus2);
        availableStatuses.add(availableStatus3);
        availableStatuses.add(availableStatus4);


//        img = mImageUrls.toArray(new String[0]);

        doctorArray = doctorNames.toArray(new String[0]);
        availableArray = availableStatuses.toArray(new String[0]);

        MyAdapter adapter = new MyAdapter(getApplicationContext(), doctorArray, img, vid, availableArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent browserIntent3 = new Intent(getApplicationContext(), AddNewEpisode.class);
//                browserIntent3.putExtra("dramaName", mNames.get(position));
//                startActivity(browserIntent3);
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(AdminDoctorAvailable.this);
                builderSingle.setIcon(R.drawable.identification);
                builderSingle.setTitle("Contact Information");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AdminDoctorAvailable.this, android.R.layout.select_dialog_item);
                arrayAdapter.add(doctorArray[position]);
                arrayAdapter.add(availableArray[position]);
                arrayAdapter.add("Email");
                arrayAdapter.add("Extension");
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
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(AdminDoctorAvailable.this);
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

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> ilink = new ArrayList<>();
        ArrayList<String> vlink = new ArrayList<>();
        String[] rTitle;
        String[] d4;
        int[] img;
        String[] v1;
        String[] rImgs;

        MyAdapter(Context c, String[] title, int[] i, String[] v, String[] d3) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.img = i;
            this.v1 = v;
            this.d4 = d3;


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);
//            myTitle.setTextColor(ContextCompat.getColor(context, R.color.white));
//            myDescription.setTextColor(ContextCompat.getColor(context, R.color.white));

            // now set our resources on views
//            images.setImageResource(img[position]);
            Picasso.get().load(R.drawable.doctor).into(images);
            myTitle.setText(rTitle[position]);
            myDescription.setText(d4[position]);


            return row;
        }
    }

}