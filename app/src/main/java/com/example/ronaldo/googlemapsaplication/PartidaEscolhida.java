package com.example.ronaldo.googlemapsaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PartidaEscolhida extends AppCompatActivity {
    String nome_jogadores[] = {"Joao", "Everton", "Guilherme"};
    String nome_partidas[] = {"Partida em Assu", "Partida em Natal","Partida em Ipanguaçu"};
    String latitudes_longitudes[][]= {{"-5.5775119","-36.9147857"},{"-5.7792569","-35.200916"},{"-5.5088647","-36859794"}};

    int partidas[][] = {{0,1,2,0},{0,1,2,1},{0,1,2,2}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_escolhida);
        TextView nome_partida_selecionada = (TextView) findViewById(R.id.partidaSelecionada);
        String nome;

        Intent i = getIntent();
        String lat = i.getStringExtra("latitude");

        if(lat.equals(latitudes_longitudes[0][0])){
            nome = nome_partidas[0];
        }else if(lat.equals(latitudes_longitudes[1][0])){
            nome = nome_partidas[1];
        }else{
            nome = nome_partidas[2];
        }
            nome_partida_selecionada.setText(nome);
    }
    public void gotoUserprofile(View view){
        Intent j = new Intent(PartidaEscolhida.this,);
        startActivity(j);
    }


}

