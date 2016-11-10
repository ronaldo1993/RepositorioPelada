package com.example.ronaldo.googlemapsaplication;

/**
 * Created by ronaldo on 08/11/2016.
 */
public class Jogador {
    String nome;
    int habilidade;
    int cordialidade;
    int pontualidade;

    public void Jogador(String nome, int habilidade, int cordialidade, int pontualidade){
        this.nome = nome;
        this.habilidade = habilidade;
        this.cordialidade = cordialidade;
        this.pontualidade = pontualidade;
    }
    public void set_Nome(String Nome){
        this.nome = Nome;
    }
    public void set_cordialidade(int cordialidade){
        this.cordialidade = cordialidade;
    }
    public void set_habilidade(int habilidade){
        this.habilidade = habilidade;
    }
    public void set_pontualidade(int pontualidade){
        this.pontualidade = pontualidade;
    }
    public int get_cordialidade(){
        return cordialidade;
    }
    public int get_habilidade(){
        return habilidade;
    }
    public int get_pontualidade(){
        return pontualidade;
    }
    public String get_nome(){
        return nome;
    }
}
