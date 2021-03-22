package com.uts_33459;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnSubmitLogin;
    ActionBar actionBar;
    EditText etUsername;
    EditText etPassword;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSubmitLogin = findViewById(R.id.button_submit_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        actionBar = getSupportActionBar();
        // Session Manager
        session = new SessionManagement(getApplicationContext());

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));

        btnSubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Check if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    // For testing puspose username, password is checked with sample data
                    // username = test
                    // password = test
                    if(username.equals("uasmobile") && password.equals("uasmobilegenap")){

                        // Creating user login session
                        // For testing i am string name, email as follow
                        // Use user real data
                        session.createLoginSession("user");
                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(LoginActivity.this, PlaylistActivity.class);
                        startActivity(i);
                        finish();

                    }else{
                        // username / password doesn't match
                        Toast.makeText(getApplicationContext(), "Invalid Username / Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}