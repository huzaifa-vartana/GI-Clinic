package com.example.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1, e2;
    Button b1;
    public static final String TAG = "mytag";
    String message;
    String savedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        e1 = findViewById(R.id.user_id);
        e2 = findViewById(R.id.pass);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}