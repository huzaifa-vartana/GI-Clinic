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

public class PrescribeMedicine extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "dramaname" +
            "";
    public static final int ImageBack = 1;
    private final ArrayList<String> mNames = new ArrayList<>();
    private final ArrayList<String> mVideosUrls = new ArrayList<>();
    private final ArrayList<String> mVideosUrlsslider = new ArrayList<>();
    private final ArrayList<Integer> mImageUrls = new ArrayList<Integer>();
    private final ArrayList<String> d1 = new ArrayList<>();
    private final ArrayList<String> episodeNames = new ArrayList<>();
    private final ArrayList<String> episodeVideoLinks = new ArrayList<>();
    private final ArrayList<String> episodeImageLinks = new ArrayList<>();
    private final ArrayList<String> episodeDescriptions = new ArrayList<>();
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
    String episodeName1, episodeName2, episodeName3, episodeName4;
    String[] nameArray;
    int[] imgArray;
    String[] videoArray;
    String[] descriptionArray;
    TextView textInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescribe_medicine);
        listView = findViewById(R.id.listDoctor);
        drawerLayout = findViewById(R.id.drawerDoctor);
        navigationView = findViewById(R.id.navDoctor);
        toolbar = findViewById(R.id.toolbarDoctor);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Prescribe Medicines");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        episodeName1 = "Huzaifa";
        episodeName2 = "Khimji";
        episodeName3 = "Bajwa";
        episodeName4 = "Subhash";
        episodeNames.add(episodeName1);
        episodeNames.add(episodeName2);
        episodeNames.add(episodeName3);
        episodeNames.add(episodeName4);
//        descp = "das";
        img_url = R.drawable.doctor;
//        video_url = "sadas";
//
        mImageUrls.add(img_url);
//        mVideosUrls.add(video_url);
//        d1.add(descp);

//        img = mImageUrls.toArray(new String[0]);
        vid = mVideosUrls.toArray(new String[0]);
//        d2 = d1.toArray(new String[0]);
        nameArray = episodeNames.toArray(new String[0]);

        MyAdapter adapter = new MyAdapter(getApplicationContext(), nameArray, img, vid, d2);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent browserIntent3 = new Intent(getApplicationContext(), AddNewEpisode.class);
//                browserIntent3.putExtra("dramaName", mNames.get(position));
//                startActivity(browserIntent3);
//                AlertDialog.Builder builderSingle = new AlertDialog.Builder(PrescribeMedicine.this);
//                builderSingle.setIcon(R.drawable.patient);
//                builderSingle.setTitle("Full Details");
//
//                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PrescribeMedicine.this, android.R.layout.select_dialog_item);
//                arrayAdapter.add("Name: " + episodeNames.get(position));
//                arrayAdapter.add("Reg #: 2017192");
//                arrayAdapter.add("Faculty: FCS");
//                arrayAdapter.add("Last Appointment: 25 December, 2020 - 16.00");
//                arrayAdapter.add("Regular Doctor: Dr Subash");
//
//                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String strName = arrayAdapter.getItem(which);
//                        AlertDialog.Builder builderInner = new AlertDialog.Builder(PrescribeMedicine.this);
//                        builderInner.setMessage(strName);
//                        builderInner.setTitle("Full Details");
//                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                        builderInner.show();
//                    }
//                });
//                builderSingle.show();


                openDialog(episodeNames.get(position));
//                Toast.makeText(getApplicationContext(), episodeNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void openDialog(String patientName) {
        LayoutInflater inflater = LayoutInflater.from(PrescribeMedicine.this);
        View subView = inflater.inflate(R.layout.edittext_builder, null);
        final EditText subEditText = subView.findViewById(R.id.dialogEditText);
        final ImageView subImageView = subView.findViewById(R.id.image);
//        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        subImageView.setImageDrawable(drawable);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Prescription for " + patientName);
//        builder.setMessage("Prescription fo");
        builder.setView(subView);
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                textInfo.setText(subEditText.getText().toString());
                Toast.makeText(PrescribeMedicine.this, "Prescription sent to the Patient", Toast.LENGTH_LONG).show();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PrescribeMedicine.this, "Cancel", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), DoctorHome.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Home", 0).show();
                finish();
                break;
            case R.id.book:
                Intent browserIntent1 = new Intent(getApplicationContext(), AllAppointments.class);
                startActivity(browserIntent1);
                finish();
                Toast.makeText(getApplicationContext(), "All Appointments", 0).show();

                break;
            case R.id.doctor:
                Intent browserIntent2 = new Intent(getApplicationContext(), PatientData.class);
                startActivity(browserIntent2);
                finish();
                Toast.makeText(getApplicationContext(), "Patient Data", 0).show();

                break;
            case R.id.cancel:
                Intent browserIntent3 = new Intent(getApplicationContext(), DoctorCancelApp.class);
                startActivity(browserIntent3);
                finish();
                Toast.makeText(getApplicationContext(), "Cancel Appointment", 0).show();

                break;
            case R.id.updates:
                Intent browserIntent4 = new Intent(getApplicationContext(), PrescribeMedicine.class);
                startActivity(browserIntent4);
                finish();
                Toast.makeText(getApplicationContext(), "Prescribe Medicine", 0).show();

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
            Intent intent = new Intent(getApplicationContext(), DoctorHome.class);
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
            Picasso.get().load(R.drawable.patient).into(images);
            myTitle.setText(rTitle[position]);
//            myDescription.setText(d4[position]);


            return row;
        }
    }

}