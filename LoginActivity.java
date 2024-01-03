package com.example.nmta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nmta.R;
import com.example.nmta.data.Account.FirebaseAccount;

public class LoginActivity extends AppCompatActivity {
    Button bt_login;
    TextView bt_signin;

    EditText edt_user, edt_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        FirebaseAccount firebaseAccount = new FirebaseAccount();

        bt_login = (Button) findViewById(R.id.bt_login_login);
        bt_signin = (TextView) findViewById(R.id.bt_signin_Login);
        edt_user = (EditText) findViewById(R.id.edt_user_login);
        edt_pass = (EditText) findViewById(R.id.edt_pass_login);

        bt_login.setOnClickListener(view -> {
            String email = edt_user.getText().toString();
            String passWord = edt_pass.getText().toString();
            firebaseAccount.LoginWithEmailandPassword(email, passWord, LoginActivity.this);
            firebaseAccount.getNameAccount(LoginActivity.this);
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
       });

            bt_signin.setOnClickListener(view -> {
                Intent i = new Intent(LoginActivity.this, SignInActivity.class);
                startActivity(i);
            });
        }
}

