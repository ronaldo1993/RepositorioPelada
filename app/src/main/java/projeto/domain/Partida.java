package projeto.domain;

/**
 * Created by ronaldo on 06/12/2016.
 */

public class Partida {
    public int id_local;
    public int horario;
    public String data;

    public Partida(int id_local, int horario, String data){
        this.id_local = id_local;
        this.horario = horario;
        this.data = data;
    }
}
