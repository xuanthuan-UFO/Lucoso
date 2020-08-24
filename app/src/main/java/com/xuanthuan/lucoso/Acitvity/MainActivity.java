package com.xuanthuan.lucoso.Acitvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xuanthuan.lucoso.R;

public class MainActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnlogin;

    String email, password;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        auth = FirebaseAuth.getInstance();
        signIn();
    }


    private void signIn() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edtEmail.getText().toString().trim();
                password = edtPassword.getText().toString().trim();
                if (!(email.isEmpty()) & !(password.isEmpty())) {
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Toast.makeText(MainActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Nhập email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPass);
        btnlogin = findViewById(R.id.login);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(new Intent(MainActivity.this, ActivityHome.class));
        }
    }

}