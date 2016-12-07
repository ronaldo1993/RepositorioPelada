package projeto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.example.ronaldo.googlemapsaplication.R;

public class PartidaEscolhida extends AppCompatActivity {
    Localizacoes l;
    String nome_local,nome_user,id_local;
    java.text.SimpleDateFormat mydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_escolhida);
        TextView nome_partida_selecionada = (TextView) findViewById(R.id.partidaSelecionada);

        nome_local = getIntent().getStringExtra("nome_local");
        nome_user = getIntent().getStringExtra("nome_user");
        id_local = getIntent().getStringExtra("id_local");


        nome_partida_selecionada.setText(nome_local);

    }

    public void gotoUserprofile(View view){
        Intent j = new Intent(PartidaEscolhida.this,Userprofile.class);
        startActivity(j);
    }
}

