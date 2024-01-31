package com.example.testvideo_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EnterUser extends AppCompatActivity {
    EditText editText;
    Button bt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enteruser);
        init();

        bt.setOnClickListener(view -> {
            String temp = editText.getText().toString();
            Intent i = new Intent(EnterUser.this,MainActivity.class);
            i.setData("asdasd");
            startActivity(i);
        });
    }

    private void init(){
        editText = findViewById(R.id.enterUser_tv);
        bt = findViewById(R.id.enterUser_bt);
    }
}
