package com.example.ronaldo.googlemapsaplication;

import java.util.HashMap;

/**
 * Created by ronaldo on 08/11/2016.
 */
public class Partida {

    int intervalo_do_dia;
    HashMap<String,Jogador> jogadores = new HashMap<String,Jogador>();

    public void Partida(){
        jogadores = new HashMap<String,Jogador>();
    }

    public boolean remover_jogador(String id_player){
        if(jogadores.containsKey(id_player)){
            jogadores.remove(id_player);
            return true;
        }
        return false;
    }

    public void inserir_jogador(String id_player,Jogador player){

        jogadores.put(id_player,player);
        /*
        {

            if (jogadores.containsKey(id_player))
                return false;
        }
        jogadores.put(id_player,player);*/

    }
    public void inserir_intervalo(int value){
        this.intervalo_do_dia = value;
    }
}
