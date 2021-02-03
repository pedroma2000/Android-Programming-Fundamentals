package pt.ipp.estg.tourpediaexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Toolbar toolbar;
    private Context context;

    private boolean isWifiConn = false;
    private boolean isMobileConn = false;

    private List<Place> places;
    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tour Expedia");
        setSupportActionBar(toolbar);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnectivity();

        if(isWifiConn || isMobileConn){
            getApi().getListOfPlaces("London", "attraction", "Science").enqueue(new Callback<List<Place>>() {
                @Override
                public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                    places = response.body();
                    addMarkers();
                    zoomToLocation();
                }

                @Override
                public void onFailure(Call<List<Place>> call, Throwable t) {
                    Log.d("Test","ERRO!" + t.getCause());
                    t.printStackTrace();
                }
            });
        }
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder().baseUrl("http://tour-pedia.org/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private TourDataAPI getApi() {
        return getRetrofit().create(TourDataAPI.class);
    }

    private void checkConnectivity () {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        for(Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn = networkInfo.isConnected();
            }
            if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn = networkInfo.isConnected();
            }
        }

        Log.d("Test", "Wifi connected: " + isWifiConn);
        Log.d("Test", "Mobile connected: " + isMobileConn);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private void addMarkers(){
        for (Place place : places) {
            addMarker(place);
        }
    }

    private void addMarker(Place place) {
        LatLng latLng = new LatLng(place.getLat(), place.getLng());

        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(place.getName())
                .snippet(place.getAddress())
        );
    }

    private void zoomToLocation() {
        LatLng latLng = new LatLng(places.get(4).getLat(), places.get(4).getLng());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        googleMap.animateCamera(cameraUpdate);
    }
}