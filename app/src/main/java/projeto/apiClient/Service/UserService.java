package projeto.apiClient.Service;

import java.util.List;

import projeto.domain.Local;
import projeto.domain.LoginResult;
import projeto.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ronaldo on 06/12/2016.
 */



public interface UserService {


    public static final String BASE_URL = "http://192.168.1.110:7000/";
    @GET("login/{nome}/{senha}")
    Call<LoginResult> get_login_result(@Path("nome") String nome, @Path("senha") String senha);


    @POST("user")
    Call<Void> register_user(@Body User body);

    @POST("partida")
    Call<Void> register_partida(@Body projeto.domain.Partida body);

    @GET("local")
    Call<List<Local>> get_locais();
}
