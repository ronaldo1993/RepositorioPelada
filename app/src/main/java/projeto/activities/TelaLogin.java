package projeto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ronaldo.googlemapsaplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import projeto.apiClient.Service.UserService;
import projeto.domain.LoginResult;
import projeto.domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TelaLogin extends AppCompatActivity {

    private static final String TAG = "tentando post: ";
    private String username;
    private String password;
    private EditText ETusername;
    private EditText ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
    }
    private void log_user(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService UserAPI = retrofit.create(UserService.class);
        Call<LoginResult> getLogin = UserAPI.get_login_result(username,password);
        getLogin.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Logou!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(TelaLogin.this, MainActivity.class);
                    i.putExtra("nome",username);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Usuario/Senha Incorreta", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Sem acesso ao servidor", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void logar(View view){

        ETusername = (EditText) findViewById(R.id.usernametextlogin);
        ETpassword = (EditText) findViewById(R.id.passwordtext);
        username = ETusername.getText().toString();
        password = ETpassword.getText().toString();
        log_user();


    }
    public void cadastrar(View view){

        Intent i = new Intent(TelaLogin.this, RegisterActivity.class);
        startActivity(i);
    }

    public void efetuar_post_partida(View view) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService UserAPI = retrofit.create(UserService.class);

        /*finally send the user of the sign up activity*/

        projeto.domain.Partida pa = new projeto.domain.Partida(1,2,"12-2016-05");
        Call<Void> callPostUser = UserAPI.register_partida(pa);

        /*Assyncronous task in background*/
        callPostUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "erroPost: " + response.code());
                    Toast.makeText(getApplicationContext(),"Usuário já existe!",Toast.LENGTH_LONG).show();
                } else { /*Successful post*/
                    Toast.makeText(getApplicationContext(),"Post executado",Toast.LENGTH_LONG).show();
                }
            }
            /*Conexion problem*/
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Falha na conexão!",Toast.LENGTH_LONG).show();
                Log.i(TAG, "erroPost: " + t.getMessage());
            }
        });
    }
}
