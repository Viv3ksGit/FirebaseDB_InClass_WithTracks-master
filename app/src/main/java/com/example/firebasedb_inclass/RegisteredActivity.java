package com.example.firebasedb_inclass;

import android.app.ProgressDialog;
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


public class RegisteredActivity extends AppCompatActivity {

    EditText EmailTxt,Passwrd1Txt,Passwrd2Txt;
    Button RegisteredBtn;

    ProgressDialog progressdialog;

    FirebaseAuth authDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);


        authDb = FirebaseAuth.getInstance();

        EmailTxt = findViewById(R.id.EmailTxt);
        Passwrd1Txt = findViewById(R.id.Passwrd1Txt);
        Passwrd2Txt = findViewById(R.id.Passwrd2Txt);
        RegisteredBtn = findViewById(R.id.RegisteredBtn);

        progressdialog = new ProgressDialog(this);
    }


    public void registeredUser(View view) {

        String email = EmailTxt.getText().toString();
        String password = Passwrd1Txt.getText().toString();
        String confirmpassword = Passwrd2Txt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(this, "Please enter a confirm password", Toast.LENGTH_LONG).show();
            return;

        }

        if(!password.equals(confirmpassword)){
            Toast.makeText(this, "Password do not match", Toast.LENGTH_LONG).show();
            return;
        }

        progressdialog.setMessage("Registering account..");
        progressdialog.show();


        authDb.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(RegisteredActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegisteredActivity.this, "Registration is failed", Toast.LENGTH_SHORT).show();
                }

                progressdialog.dismiss();
            }


        });
    }}