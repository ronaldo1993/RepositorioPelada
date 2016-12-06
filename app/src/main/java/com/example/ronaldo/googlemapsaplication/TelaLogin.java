package com.example.ronaldo.googlemapsaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
    }

    public void logar(View view){
        Intent i = new Intent(TelaLogin.this, MainActivity.class);
        startActivity(i);
    }
    public void register(View view){
        Intent i = new Intent(TelaLogin.this, RegisterActivity.class);
        startActivity(i);

    }
}
