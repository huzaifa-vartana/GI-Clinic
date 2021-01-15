package com.example.adminpanel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;
    MaterialTextView materialTextView;
    public static final String TAG = "mytag";
    private FirebaseAuth firebaseAuth;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = findViewById(R.id.signButton);
        b2 = findViewById(R.id.signUpButton);
        e1 = findViewById(R.id.emailText);
        materialTextView = findViewById(R.id.forgetPassword);
        e2 = findViewById(R.id.passwordText);
        firebaseAuth = FirebaseAuth.getInstance();
        radioGroup = findViewById(R.id.radioGroup);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        materialTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPassword();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void forgetPassword() {
        Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
        startActivity(intent);
    }

    public void signIn() {
        String email = "a@a.com";
        String pass = "admin.";
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(pass)) {
//            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Select your option", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "You have been Logged in", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            Toast.makeText(getApplicationContext(), radioButton.getText(), 0).show();
                            if (radioButton.getText().equals("Patient")) {
                                Intent intent = new Intent(getApplicationContext(), PatientHome.class);
                                startActivity(intent);
                                finish();
                            } else if (radioButton.getText().equals("Admin Staff")) {
                                Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                                startActivity(intent);
                                finish();
//                                Toast.makeText(getApplicationContext(), "Admin Selected", 0).show();
                            } else if (radioButton.getText().equals("Doctor")) {
                                Intent intent = new Intent(getApplicationContext(), DoctorHome.class);
                                startActivity(intent);
                                finish();
//                                Toast.makeText(getApplicationContext(), "Doctor Selected", 0).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });

    }

    public void checkButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
//        Toast.makeText(getApplicationContext(), radioButton.getText(), 0).show();
    }
}