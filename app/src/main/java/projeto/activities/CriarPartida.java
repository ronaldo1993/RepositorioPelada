package projeto.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ronaldo.googlemapsaplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CriarPartida extends AppCompatActivity {
    DatePickerDialog dataDialog;
    EditText dataText;
    SimpleDateFormat formatador;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_partida);
        formatador = new SimpleDateFormat("dd/MM/yyyy");
        dataText = (EditText) findViewById(R.id.partidadatatext);
        //Colocando teclado especial para data
        Calendar novaData = Calendar.getInstance();
        dataDialog = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener(){
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth){
                        Calendar data = Calendar.getInstance();
                        data.set(year, monthOfYear, dayOfMonth);
                        dataText.setText(formatador.format(data.getTime()));
                    }
                },novaData.get(Calendar.YEAR), novaData.get(Calendar.MONTH), novaData.get(Calendar.DAY_OF_MONTH));
        btnCadastrar = (Button) findViewById(R.id.confirmbtn);
        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                confirmarDialog();
            }
        });
        // Impedindo usuário de digitar ao usar o date picker
        dataText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if(hasFocus)
                    dataDialog.show();
            }
        });
        dataText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dataDialog.show();
            }
        });


    }

    public void confirmarDialog(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção");
        alerta.setMessage("Deseja realmente cadastrar?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                //salvar no banco
                finish();
            }
        });
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        alerta.show();
    }

    public void matchCreated(View view)
    {
        Intent i= new Intent(CriarPartida.this,MainActivity.class);
        startActivity(i);
    }


}
