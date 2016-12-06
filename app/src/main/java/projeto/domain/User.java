package projeto.domain;

/**
 * Created by ronaldo on 06/12/2016.
 */

public class User {
    public String nome;
    public int habilidade;
    public int coordialidade;
    public int pontualidade;
    public int numeroT;
    public String senha;


    public User(String nome, int habilidade, int coordialidade, int pontualidade, int numeroT, String senha){
        this.nome = nome;
        this.habilidade = habilidade;
        this.coordialidade = coordialidade;
        this.pontualidade = pontualidade;
        this.numeroT = numeroT;
        this.senha = senha;
    }
}
