package projeto.apiClient.Service;

import projeto.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ronaldo on 06/12/2016.
 */

public interface UserService {
    public static final String BASE_URL = "http://192.168.1.110:7000/";

    @POST("user")
    Call<Void> register_user(@Body User body);

    @POST("partida")
    Call<Void> register_partida(@Body projeto.domain.Partida body);
}
