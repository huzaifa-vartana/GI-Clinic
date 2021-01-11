package com.example.adminpanel;

import android.content.Context;
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

public class ConfirmAppointment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "dramaname" +
            "";
    public static final int ImageBack = 1;
    //    String[] descriptionArray;
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mVideosUrls = new ArrayList<>();
//    private ArrayList<String> mVideosUrlsslider = new ArrayList<>();
//    private ArrayList<Integer> mImageUrls = new ArrayList<Integer>();
    private final ArrayList<String> appointmentDatesTimes = new ArrayList<>();
    private final ArrayList<String> patientNames = new ArrayList<>();
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
    //    String episodeName1, episodeName2, episodeName3, episodeName4;
//    String[] nameArray;
//    int[] imgArray;
//    String[] videoArray;
//    String[] descriptionArray;
//    private ArrayList<String> mNames = new ArrayList<>();
//    private ArrayList<String> mVideosUrls = new ArrayList<>();
//    private ArrayList<String> mVideosUrlsslider = new ArrayList<>();
//    private ArrayList<Integer> mImageUrls = new ArrayList<Integer>();
//    private ArrayList<String> d1 = new ArrayList<>();
//    private ArrayList<String> episodeNames = new ArrayList<>();
//    private ArrayList<String> episodeVideoLinks = new ArrayList<>();
//    private ArrayList<String> episodeImageLinks = new ArrayList<>();
    String patientName1, patientName2, patientName3, patientName4;
    String appointmentDateTime1, appointmentDateTime2, appointmentDateTime3, appointmentDateTime4;
    String[] nameArray;
    String[] dateTimeArray;

//    private ArrayList<String> episodeDescriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmappointment);
        listView = findViewById(R.id.listDoctor);
        drawerLayout = findViewById(R.id.drawerDoctor);
        navigationView = findViewById(R.id.navDoctor);
        toolbar = findViewById(R.id.toolbarDoctor);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Confirm Appointments");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        patientName1 = "Ahmed";
        patientName2 = "Hamza";
        patientName3 = "Salman";
        patientName4 = "Noman";
        appointmentDateTime1 = "3-01-2021 16.00";
        appointmentDateTime2 = "3-01-2021 16.30";
        appointmentDateTime3 = "4-01-2021 16.00";
        appointmentDateTime4 = "5-01-2021 16.00";
        patientNames.add(patientName1);
        patientNames.add(patientName2);
        patientNames.add(patientName3);
        patientNames.add(patientName4);
        appointmentDatesTimes.add(appointmentDateTime1);
        appointmentDatesTimes.add(appointmentDateTime2);
        appointmentDatesTimes.add(appointmentDateTime3);
        appointmentDatesTimes.add(appointmentDateTime4);
////        descp = "das";
//        img_url = R.drawable.doctor;
////        video_url = "sadas";
////
//        mImageUrls.add(img_url);
////        mVideosUrls.add(video_url);
////        d1.add(descp);
//
////        img = mImageUrls.toArray(new String[0]);
//        vid = mVideosUrls.toArray(new String[0]);
////        d2 = d1.toArray(new String[0]);
        nameArray = patientNames.toArray(new String[0]);
        dateTimeArray = appointmentDatesTimes.toArray(new String[0]);

        MyAdapter adapter = new MyAdapter(getApplicationContext(), nameArray, img, vid, dateTimeArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent3 = new Intent(getApplicationContext(), AssignDoctor.class);
                browserIntent3.putExtra("patientName", patientNames.get(position));
                startActivity(browserIntent3);
                Toast.makeText(getApplicationContext(), "Select the doctor to assign to patient", Toast.LENGTH_SHORT).show();

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
            Picasso.get().load(R.drawable.man).into(images);
            myTitle.setText(rTitle[position]);
            myDescription.setText(d4[position]);


            return row;
        }
    }

}