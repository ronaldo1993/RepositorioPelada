package projeto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ronaldo.googlemapsaplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import projeto.apiClient.Service.UserService;
import projeto.domain.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ronaldo.googlemapsaplication.R.id.editText;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Erro de Post user";
    private String username;
    private String password;
    private int tnumber;
    private EditText ETusername;
    private EditText ETpassword;
    private EditText ETtnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void confirm_register(View view) {
        ETusername = (EditText) findViewById(R.id.usernametext);
        ETpassword = (EditText) findViewById(R.id.ptext);
        ETtnumber = (EditText) findViewById(R.id.tntext);
        username = ETusername.getText().toString();
        password = ETpassword.getText().toString();
        tnumber = Integer.parseInt(ETtnumber.getText().toString());
        cadastrar_user();

    }
    public void cadastrar_user() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService UserAPI = retrofit.create(UserService.class);

        /*finally send the user of the sign up activity*/

        User us = new User(username,0, 0, 0, tnumber, password);
        Call<Void> callPostUser = UserAPI.register_user(us);

        /*Assyncronous task in background*/
        callPostUser.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.i(TAG, "erroPost: " + response.code());
                    Toast.makeText(getApplicationContext(),"Usuário já existe!",Toast.LENGTH_LONG).show();
                } else { /*Successful post*/
                    Toast.makeText(getApplicationContext(),"Cadastrado com Sucesso!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this,TelaLogin.class);
                    startActivity(i);
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
