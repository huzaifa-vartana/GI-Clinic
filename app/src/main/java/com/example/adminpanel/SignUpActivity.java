package com.example.adminpanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "mytag";
    Button b1, b2;
    EditText e1, e2;
    MaterialTextView materialTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1 = findViewById(R.id.createAccount);
        b2 = findViewById(R.id.backLogin);
        e1 = findViewById(R.id.emailTextNewAccount);
        e2 = findViewById(R.id.passwordTextNewAccount);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Check your email for further instructions", 0).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}