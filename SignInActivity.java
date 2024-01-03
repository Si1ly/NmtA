package com.example.nmta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nmta.R;
import com.example.nmta.data.Account.FirebaseAccount;

public class SignInActivity extends AppCompatActivity {
    EditText edt_email_signin,edt_pass_signin, edt_username_signin;
    Button create_account_signin,bt_login_signin;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        FirebaseAccount firebaseAccount = new FirebaseAccount();

        init();
        create_account_signin.setOnClickListener(v -> {
            String username = edt_username_signin.getText().toString();
            String email = edt_email_signin.getText().toString();
            String pass = edt_pass_signin.getText().toString();
            firebaseAccount.CreateAccountwithEmailandPassword(email,pass,SignInActivity.this);
        });

        bt_login_signin.setOnClickListener(v -> {
            Intent i = new Intent(SignInActivity.this,LoginActivity.class);
            startActivity(i);
        });
    }

    public void init(){
        edt_pass_signin = (EditText) findViewById(R.id.edt_pass_signin);
        edt_email_signin = (EditText) findViewById(R.id.edt_email_signin);
        edt_username_signin = (EditText) findViewById(R.id.edt_user_signin);
        create_account_signin = (Button) findViewById(R.id.bt_create_signin);
        bt_login_signin = (Button) findViewById(R.id.bt_login_signin);
    }
}
