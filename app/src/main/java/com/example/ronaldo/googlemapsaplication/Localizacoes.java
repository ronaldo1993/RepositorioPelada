package com.example.ronaldo.googlemapsaplication;


import java.util.HashMap;

/**
 * Created by ronaldo on 03/11/2016.
 */

public class Localizacoes {
    private HashMap<String,HashMap<String, Partidas>> todas_loc = new HashMap<String,HashMap<String, Partidas>>();

    public void Localizacoes(){
        todas_loc = new HashMap<String,HashMap<String, Partidas>>();
    }

    public boolean remover_local(String latitude, String longitude){

        if(todas_loc.containsKey(latitude)){
            if(todas_loc.get(latitude).containsKey(longitude)){

                todas_loc.remove(latitude);
                return true;
            }
        }
        return false;
    }
    public boolean inserir_local(String latitude, String longitude, Partidas pt){
        HashMap<String,Partidas> local_generico;

        if(todas_loc.containsKey(latitude)){
            if(!todas_loc.get(latitude).containsKey(longitude)){

                local_generico = new HashMap<String,Partidas>();
                local_generico.put(longitude,pt);
                todas_loc.put(latitude,local_generico);
                return true;
            }
        }
        else{
            local_generico = new HashMap<String,Partidas>();
            local_generico.put(longitude,pt);
            todas_loc.put(latitude,local_generico);
            return true;
        }
        return false;
    }
    public boolean inserir_local(String latitude, String longitude){
        HashMap<String,Partidas> local_generico;

        if(todas_loc.containsKey(latitude)){
            if(!todas_loc.get(latitude).containsKey(longitude)){
                Partidas aux = new Partidas();

                local_generico = new HashMap<String,Partidas>();
                local_generico.put(longitude,aux);
                todas_loc.put(latitude,local_generico);
                return true;
            }
        }
        else{
            Partidas aux = new Partidas();

            local_generico = new HashMap<String,Partidas>();
            local_generico.put(longitude,aux);
            todas_loc.put(latitude,local_generico);
            return true;
        }
        return false;
    }

    public Partidas get_loc(String latitude, String longitude){
        /*Partidas minha = new Partidas();
        minha.set_nome("passou");
        if(todas_loc.containsKey(latitude)){

            if(todas_loc.containsKey(longitude))
                return minha;
                /*return todas_loc.get(latitude).get(longitude);*/
        //}
        return todas_loc.get(latitude).get(longitude);//*/
        //return null;
    }

}
