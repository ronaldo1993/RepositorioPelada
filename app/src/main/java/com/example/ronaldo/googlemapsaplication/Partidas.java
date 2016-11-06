package com.example.ronaldo.googlemapsaplication;


import java.util.HashMap;

/**
 * Created by ronaldo on 03/11/2016.
 */

public class Partidas {
    private HashMap<String,HashMap<String, Partida>> todas_partidas;

    public void Partidas(){
        todas_partidas = new HashMap<String,HashMap<String, Partida>>();
    }

    public boolean remover_partida(String latitude, String longitude){

        if(todas_partidas.containsKey(latitude)){
            if(todas_partidas.get(latitude).containsKey(longitude)){

                todas_partidas.remove(latitude);
                return true;
            }
        }
        return false;
    }
    public boolean inserir_partida(String latitude, String longitude, Partida pt){
        HashMap<String,Partida> partida_generica;

        if(todas_partidas.containsKey(latitude)){
            if(!todas_partidas.get(latitude).containsKey(longitude)){

                partida_generica = new HashMap<String,Partida>();
                partida_generica.put(longitude,pt);
                todas_partidas.put(latitude,partida_generica);
                return true;
            }
        }
        else{
            partida_generica = new HashMap<String,Partida>();
            partida_generica.put(longitude,pt);
            todas_partidas.put(latitude,partida_generica);
            return true;
        }
        return false;
    }
}
