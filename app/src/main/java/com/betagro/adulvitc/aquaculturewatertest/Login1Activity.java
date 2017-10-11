package com.betagro.adulvitc.aquaculturewatertest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login1Activity extends AppCompatActivity {

    private EditText userEditText, passEditText;
    private TextView registryTextView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        EditText userEditText = (EditText) findViewById(R.id.etUsername);
        EditText passEditText = (EditText) findViewById(R.id.etPassword);
        TextView registryTextView = (TextView) findViewById(R.id.tvRegisterLink);
        Button loginButton = (Button) findViewById(R.id.bSignIn);


        registryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login1Activity.this, RegistryActivity.class);
                startActivity(intent);
            }
        });





    }// Main Method



}// Main Class
