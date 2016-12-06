package projeto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.example.ronaldo.googlemapsaplication.R;

public class PartidaEscolhida extends AppCompatActivity {
    String nome_jogadores[] = {"Joao", "Everton", "Guilherme"};
    String nome_partidas[] = {"Partida em Assu", "Partida em Natal","Partida em Ipanguaçu"};
    String latitudes_longitudes[][]= {{"-5.5775119","-36.9147857"},{"-5.7792569","-35.200916"},{"-5.5088647","-36859794"}};

    Localizacoes l;
    java.text.SimpleDateFormat mydate;

    int partidas[][] = {{0,1,2,0},{0,1,2,1},{0,1,2,2}};

    /*@Override
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
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_escolhida);
        TextView nome_partida_selecionada = (TextView) findViewById(R.id.partidaSelecionada);
        String nome;

        //nome_partida_selecionada.setText("partida");

        iniciar();

        Intent i = getIntent();
        String lat = i.getStringExtra("latitude");
        String lng = i.getStringExtra("longitude");

        Partidas localidade = l.get_loc(lat,lng);

        //Partida pa = null;

        if(localidade!=null) {
            nome_partida_selecionada.setText(localidade.get_nome());
        }
        else{
            nome_partida_selecionada.setText("partida");
        }
        //pa = localidade.get_partida(mydate,8);

/*
        nome = localidade.get_nome();*/

        //nome_partida_selecionada.setText(lat);
    }

    public void iniciar(){

        Jogador Ronaldo = null;
        Jogador Gabriel = null;
        Jogador Douglas = null;
        Jogador Rubens = null;
        Jogador Jefferson = null;
        Jogador Victor = null;
        Jogador Joao = null;
        Jogador Mario = null;
        Jogador Robson = null;

        Ronaldo = new Jogador();
        Ronaldo.set_cordialidade(3);
        Ronaldo.set_habilidade(3);
        Ronaldo.set_Nome("Ronaldo");
        Ronaldo.set_pontualidade(5);

        Gabriel = new Jogador();
        Gabriel.set_cordialidade(5);
        Gabriel.set_habilidade(4);
        Gabriel.set_Nome("Gabriel");
        Gabriel.set_pontualidade(2);

        Douglas = new Jogador();
        Douglas.set_cordialidade(4);
        Douglas.set_habilidade(5);
        Douglas.set_Nome("Douglas");
        Douglas.set_pontualidade(4);

        Rubens = new Jogador();
        Rubens.set_cordialidade(5);
        Rubens.set_habilidade(5);
        Rubens.set_Nome("Rubens");
        Rubens.set_pontualidade(5);

        Jefferson = new Jogador();
        Jefferson.set_cordialidade(5);
        Jefferson.set_habilidade(5);
        Jefferson.set_Nome("Jefferson");
        Jefferson.set_pontualidade(5);

        Victor = new Jogador();
        Victor.set_cordialidade(4);
        Victor.set_habilidade(5);
        Victor.set_Nome("Victor");
        Victor.set_pontualidade(1);

        Joao = new Jogador();
        Joao.set_cordialidade(5);
        Joao.set_habilidade(5);
        Joao.set_Nome("João");
        Joao.set_pontualidade(3);

        Mario = new Jogador();
        Mario.set_cordialidade(5);
        Mario.set_habilidade(5);
        Mario.set_Nome("Mario");
        Mario.set_pontualidade(5);

        Robson = new Jogador();
        Robson.set_cordialidade(1);
        Robson.set_habilidade(5);
        Robson.set_Nome("Robson");
        Robson.set_pontualidade(5);


        Partida capim_macio1 = new Partida();
        capim_macio1.inserir_intervalo(8);

        Partida capim_macio2 = new Partida();
        capim_macio2.inserir_intervalo(9);

        Partida capim_macio3 = new Partida();
        capim_macio3.inserir_intervalo(10);

        capim_macio1.inserir_jogador("1",Douglas);
        capim_macio1.inserir_jogador("2",Gabriel);
        capim_macio1.inserir_jogador("3",Ronaldo);

        capim_macio2.inserir_jogador("4",Victor);
        capim_macio2.inserir_jogador("5",Jefferson);
        capim_macio2.inserir_jogador("6",Robson);

        capim_macio3.inserir_jogador("7",Joao);
        capim_macio3.inserir_jogador("8",Mario);

        Partidas gamesCapim = new Partidas();
        Partidas gamesZonaNorte = new Partidas();
        Partidas gamesIFRN = new Partidas();

        Partida zona_norte1 = new Partida();
        zona_norte1.inserir_intervalo(8);

        zona_norte1.inserir_jogador("1",Victor);
        zona_norte1.inserir_jogador("2",Ronaldo);
        zona_norte1.inserir_jogador("3",Robson);

        Partida zona_norte2 = new Partida();
        zona_norte2.inserir_intervalo(9);

        zona_norte2.inserir_jogador("1",Joao);
        zona_norte2.inserir_jogador("2",Rubens);
        zona_norte2.inserir_jogador("3",Robson);

        java.text.SimpleDateFormat mydateNorte = new java.text.SimpleDateFormat("19.11.2016");
        gamesZonaNorte.inserir_partida(mydateNorte,zona_norte1);

        gamesZonaNorte.inserir_partida(mydateNorte,zona_norte2);

        mydate = new java.text.SimpleDateFormat("19.11.2016");
        gamesCapim.inserir_partida(mydate,capim_macio1);
        gamesCapim.inserir_partida(mydate,capim_macio2);
        gamesCapim.inserir_partida(mydate,capim_macio3);

        gamesCapim.set_nome("Capim Macio");
        gamesZonaNorte.set_nome("Zona Norte");
        gamesIFRN.set_nome("IFRN");

        l = new Localizacoes();
        l.inserir_local("-5.8512958","-35.2044984",gamesCapim);
        l.inserir_local("-5.7592423","-35.2510852",gamesZonaNorte);
        l.inserir_local("-5.7494198","-35.2608199",gamesIFRN);
    }
    public void gotoUserprofile(View view){
        Intent j = new Intent(PartidaEscolhida.this,Userprofile.class);
        startActivity(j);
    }
}

