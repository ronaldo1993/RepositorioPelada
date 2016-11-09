package com.example.ronaldo.googlemapsaplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by ronaldo on 03/11/2016.
 */

public class Partidas {
    HashMap<SimpleDateFormat, List<Partida>> dias_inteiros = new HashMap<SimpleDateFormat, List<Partida>>();

    String nome;

    public void Partidas(){
        dias_inteiros = new HashMap<SimpleDateFormat, List<Partida>>();
    }

    public boolean inserir_partida(SimpleDateFormat data, Partida part){
        List<Partida> aux = new ArrayList<>();
        aux.add(part);
        dias_inteiros.put(data,aux);
        return true;
        /*
        if(dias_inteiros.containsKey(data)){
            dias_inteiros.get(data).add(part);
        }
        else{
            List<Partida> aux = null;
            aux.add(part);
            dias_inteiros.put(data,aux);
        }
        return false;*/
    }

    public boolean remover_partida(SimpleDateFormat data, int horario){
        if(dias_inteiros.containsKey(data)) {
            int i;

            for(i=0;i<dias_inteiros.get(data).size();i++){
                if(dias_inteiros.get(data).get(i).intervalo_do_dia == horario)
                    dias_inteiros.get(data).remove(i);
            }
            dias_inteiros.remove(data);

            return true;
        }
        return false;
    }
    public Partida get_partida(SimpleDateFormat data, int intervalo_dia){
        return dias_inteiros.get(data).get(intervalo_dia);
    }
    public void set_nome(String nome){
        this.nome = nome;
    }
    public String get_nome(){
        return nome;
    }
    /* List<Jogador> */

}
