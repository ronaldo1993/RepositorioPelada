package com.example.ronaldo.googlemapsaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CriarConta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
    }

    public void criar_conta(View view) {
        Intent i = new Intent(CriarConta.this, MainActivity.class);
        startActivity(i);
    }

    public void login(View view){
        this.finish();
    }
}
