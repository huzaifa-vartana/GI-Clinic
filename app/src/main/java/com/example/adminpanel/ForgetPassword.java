package com.example.adminpanel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    private EditText editText;
    private Button forget_button;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        editText = findViewById(R.id.emailText_forget);
        forget_button = findViewById(R.id.forgetPassword_new);
        forget_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget();
            }
        });
    }

    private void forget() {
        String email = editText.getText().toString().trim();
        if (email.length() != 0) {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {


            Toast.makeText(getApplicationContext(), "Enter the Email", Toast.LENGTH_SHORT).show();

        }

    }
}