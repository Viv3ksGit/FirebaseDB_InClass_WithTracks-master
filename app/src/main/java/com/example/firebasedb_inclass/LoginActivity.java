package com.example.firebasedb_inclass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText EmailTxt, PassTxt;
    Button   LoginBtn, CreateBtn;

    ProgressDialog progressdialog;


    FirebaseAuth Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Db = FirebaseAuth.getInstance();

        EmailTxt = findViewById(R.id.EmailTxt);
        PassTxt = findViewById(R.id.PassTxt);
        LoginBtn = findViewById(R.id.LoginBtn);
        CreateBtn = findViewById(R.id.CreateBtn);

        progressdialog = new ProgressDialog(this);

    }

    public void LoginUser(View view) {


        String email = EmailTxt.getText().toString();
        final String password = PassTxt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        }
        progressdialog.setMessage("logging in.");
        progressdialog.show();
        Db.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                        progressdialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                            finish();

                           startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }


    public void Register(View view) {

        Intent intent = new Intent(this, RegisteredActivity.class);
        startActivity(intent);


    }}