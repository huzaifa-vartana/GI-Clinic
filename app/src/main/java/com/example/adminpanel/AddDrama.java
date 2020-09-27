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

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AddDrama extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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
    ListView listView;
    String name, descp, img_url, video_url;
    StorageReference storageReference;
    boolean isaBoolean = false;
    String[] item;
    String[] img;
    String[] vid;
    String[] d2;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mVideosUrls = new ArrayList<>();
    private ArrayList<String> mVideosUrlsslider = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> d1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddrama);
        listView = findViewById(R.id.listDrama);
        drawerLayout = findViewById(R.id.drawerDrama);
        navigationView = findViewById(R.id.navDrama);
        toolbar = findViewById(R.id.toolbarDrama);
        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Drama");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Category");

        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    name = String.valueOf(dataSnapshot1.child("Title").getValue());
                    descp = String.valueOf(dataSnapshot1.child("Episodes").getValue());
                    img_url = String.valueOf(dataSnapshot1.child("Image").getValue());
                    video_url = String.valueOf(dataSnapshot1.child("Drama").getValue());
                    mNames.add(name);
                    mImageUrls.add(img_url);
                    mVideosUrls.add(video_url);
                    d1.add(descp);
                }
                item = mNames.toArray(new String[0]);
                img = mImageUrls.toArray(new String[0]);
                vid = mVideosUrls.toArray(new String[0]);
                d2 = d1.toArray(new String[0]);
                MyAdapter adapter = new MyAdapter(getApplicationContext(), item, img, vid, d2);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent browserIntent3 = new Intent(getApplicationContext(), defaultView.class);
//                browserIntent3.putExtra("dramaName", mNames.get(position));
//                startActivity(browserIntent3);
                Toast.makeText(getApplicationContext(), d1.get(position), Toast.LENGTH_SHORT).show();

            }
        });
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

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> ilink = new ArrayList<>();
        ArrayList<String> vlink = new ArrayList<>();
        String[] rTitle;
        String[] d4;
        String[] img;
        String[] v1;
        String[] rImgs;

        MyAdapter(Context c, String[] title, String[] i, String[] v, String[] d3) {
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
            //images.setImageResource(img[position]);
            Picasso.get().load(img[position]).into(images);
            myTitle.setText(rTitle[position]);
            myDescription.setText(d4[position]);


            return row;
        }
    }

}