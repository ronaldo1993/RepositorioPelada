package projeto.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronaldo.googlemapsaplication.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import projeto.apiClient.Service.UserService;
import projeto.domain.Local;
import projeto.domain.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    Marker marker;
    Marker mymarker;
    List<Local> todos_locais;
    String nome_user;


    /*Se os servicos do google estiverem disponiveis mostra mensagem 'perfect' e chama a activity_main
     setada por initMap()*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nome_user = getIntent().getStringExtra("nome");
        if (googleServicesAvailable()) {

            Toast.makeText(this, "Perfeito!", Toast.LENGTH_LONG).show();

            setContentView(R.layout.activity_main);
            initMap();
        } else {
            //no google maps layout
        }
    }

    /*Pega o frament google maps definido em activity_main.xml e atribue ao objeto mapFragment,
    e inicializa o sistema de mapas*/
    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    /*Testa se o servicos google estao disponiveis*/
    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    /*Callback chamada quando o mapa esta pronto*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        if(mGoogleMap!=null){

            /*Adiciona um Listener para escutar o evento de clicar na janela de informacao e implementa
            o metodo que captura o evento*/
            mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent intent = new Intent(MainActivity.this, PartidaEscolhida.class);
                    String lat_aux = Double.toString(marker.getPosition().latitude);
                    String lng_aux = Double.toString(marker.getPosition().longitude);

                    //passando informacoes do marcador selecionado para a proxima activity(partida selecionada)

                    //intent.putExtra("latitude", Double.toString(marker.getPosition().latitude));
                    //intent.putExtra("longitude", Double.toString(marker.getPosition().longitude));

                    for(int i=0;i<todos_locais.size();i++){
                        if(todos_locais.get(i).latitude.equals(lat_aux) && todos_locais.get(i).longitude.equals(lng_aux)){

                            intent.putExtra("id_local", todos_locais.get(i).id_local);
                            intent.putExtra("nome_user", nome_user);
                            intent.putExtra("nome_local",todos_locais.get(i).nome);

                            i=todos_locais.size();
                        }
                    }
                    //inicia a activity para a partida selecionada
                    startActivity(intent);
                }
            });

            /*Mostra a informacao do marcador clicado usando pegando o xml customizado(info_window.xml)*/

            mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null);
                    if(!marker.equals(mymarker)) {
                        //View v = getLayoutInflater().inflate(R.layout.info_window, null);

                        TextView tvLocality = (TextView) v.findViewById(R.id.tv_locality);
                        TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                        TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);
                        TextView tvSnippet = (TextView) v.findViewById(R.id.tv_snippet);

                        LatLng ll = marker.getPosition();
                        tvLocality.setText(marker.getTitle());
                        tvLng.setText("Longitude: " + ll.longitude);
                        tvLat.setText("Latitude:" + ll.latitude);
                        tvSnippet.setText(marker.getSnippet());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"minha posição",Toast.LENGTH_LONG).show();
                        marker.hideInfoWindow();
                        return null;
                    }
                    return v;
                }
            });
        }

        /*cria o cliente google para requisitar o mapa e a posicao atual e associa essa funcao como
         callback da conexao*/
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();

        /*Adiciona os icones a lugares fixos no mapa*/
        String location1 = "Capim Macio Natal";
        String location2 = "IFRN Natal";
        String location3 = "Zona Norte Natal";

        Geocoder geo_aux = new Geocoder(this);
        List<android.location.Address> list = null;
        request_server_users();


    }

    /* list = geo_aux.getFromLocationName(location1, 1);
           android.location.Address address = list.get(0);

           double lat = address.getLatitude();
           double lng = address.getLongitude();

           String localidade1 = address.getLocality();
           setMarker(localidade1,lat,lng);

           list = geo_aux.getFromLocationName(location2, 1);
           address = list.get(0);

           lat = address.getLatitude();
           lng = address.getLongitude();

           String localidade2 = address.getLocality();
           setMarker(localidade2,lat,lng);

           list = geo_aux.getFromLocationName(location3, 1);
           address = list.get(0);

           lat = address.getLatitude();
           lng = address.getLongitude();

           String localidade3 = address.getLocality();
           setMarker(localidade3,lat,lng);*/
    public void request_server_users(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserService UserAPI = retrofit.create(UserService.class);
        Call<List<Local>> getLogin = UserAPI.get_locais();

        getLogin.enqueue(new Callback<List<Local>>() {
            @Override
            public void onResponse(Call<List<Local>> call, Response<List<Local>> response) {
                if(response.isSuccessful()){
                    todos_locais = response.body();

                    for (int i = 0; i < todos_locais.size(); i++) {
                        setMarker(todos_locais.get(i).nome, Double.parseDouble(todos_locais.get(i).latitude), Double.parseDouble(todos_locais.get(i).longitude));
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Erro na resposta do servidor",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Local>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Sem acesso ao servidor",Toast.LENGTH_LONG).show();
            }
        });
    }
/*    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    } */

    /*Move a camera e da um zoom na posicao desejada*/
    private void goToLocationZoom(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);

    }

    /*Pega o valor digitado na caixa de texto e move a camera para ele*/
    public void geoLocate(View view) throws IOException {
        EditText et = (EditText) findViewById(R.id.editText);
        String location = et.getText().toString();
        Geocoder gc = new Geocoder(this);

        if(!location.equals("")) {
            List<android.location.Address> list = gc.getFromLocationName(location, 1);

            if(list.size()==0){
                Toast.makeText(this, "Localização não encontrada", Toast.LENGTH_LONG).show();
            }
            else {

                android.location.Address address = list.get(0);
                String locality = address.getLocality();

                Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

                double lat = address.getLatitude();
                double lng = address.getLongitude();

                goToLocationZoom(lat, lng, 15);
            }
        }
        else{
            Toast.makeText(this, "campo vazio!", Toast.LENGTH_LONG).show();
        }
        //setMarker(locality, lat, lng);
    }

    /*Cria um marcador no mapa com um titulo e uma localizacao*/
    private void setMarker(String locality, double lat, double lng) {
        /*if(marker!=null){
            marker.remove();
        }*/

        MarkerOptions options = new MarkerOptions()
                                    .title(locality)
                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                                    .position(new LatLng(lat,lng))
                                    .snippet("CLIQUE NESTE BALÃO \n E VEJA DETALHES");
        marker = mGoogleMap.addMarker(options);
    }

    /*Cria um marcador no mapa da minha posicao*/
    private void setMyMarker(String locality, double lat, double lng) {

        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(lat,lng));
        mymarker = mGoogleMap.addMarker(options);
    }

    /*Cria o menu para poder mudar de tipo de mapa aproveitando o menu do xml*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mapTypeHybrid:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.mapTypeSatellite:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.mapTypeNormal:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.mapTypeTerrain:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Uma vez que o aplicativo consiga efetuar a conexao, ele requisita a cada intervalo de tempo
    a posicao atual do dispositivo*/
    LocationRequest mLocationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(100000);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        setMyMarker("Eu", lastLocation.getLatitude(),lastLocation.getLongitude());
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /*Se a posicao atual mudar o aplicativo tambem pega a posicao diferente*/
    @Override
    public void onLocationChanged(Location location) {
        if(location == null){
            Toast.makeText(this,"Cant get current location!",Toast.LENGTH_LONG).show();
        }else{
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,17);
            mGoogleMap.animateCamera(update);
        }
    }

    public void pesquisar_partidas(View view) {
        Intent intent = new Intent(MainActivity.this, ProcurarPartidas.class);
        startActivity(intent);
    }

}
