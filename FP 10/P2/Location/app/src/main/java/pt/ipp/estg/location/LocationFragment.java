package pt.ipp.estg.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class LocationFragment extends Fragment {

    private static final int REQUEST_FINE_LOCATION = 100;
    private Context context;
    private Button lastLocationButton;
    private Button periodicLocationButton;
    private Button stopPeriodicLocationButton;
    private Button startMapButton;
    private TextView latTextView;
    private TextView longTextView;
    private TextView accuracyTextView;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Geocoder geocoder;
    private TextView addressTextView;
    private TextView localityTextView;
    private TextView countryTextView;
    private FragmentManager fragmentManager;
    private Location currentLocation;

    public LocationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        latTextView = view.findViewById(R.id.textView);
        longTextView = view.findViewById(R.id.textView2);
        accuracyTextView = view.findViewById(R.id.textView3);
        addressTextView = view.findViewById(R.id.textView4);
        localityTextView = view.findViewById(R.id.textView5);
        countryTextView = view.findViewById(R.id.textView6);

        geocoder = new Geocoder(context);

        lastLocationButton = view.findViewById(R.id.button);
        lastLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLastLocation();
            }
        });

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    latTextView.setText("Latitude: " + location.getLatitude());
                    longTextView.setText("Longitude: " + location.getLongitude());
                    accuracyTextView.setText("Accuracy: " + location.getAccuracy());
                    getDataFromGeoCoder(location);
                }
            }
        };

        periodicLocationButton = view.findViewById(R.id.button2);
        periodicLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationUpdates();
            }
        });

        stopPeriodicLocationButton = view.findViewById(R.id.button3);
        stopPeriodicLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLocationUpdates();
            }
        });

        startMapButton = view.findViewById(R.id.button4);
        startMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity main = (MainActivity)context;
                fragmentManager = main.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragmentContainer, new MapsFragment(context));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        latTextView.setText("Latitude: " + location.getLatitude());
                        longTextView.setText("Longitude: " + location.getLongitude());
                        accuracyTextView.setText("Accuracy: " + location.getAccuracy());
                        getDataFromGeoCoder(location);
                        currentLocation = location;
                    }
                }
            }).addOnFailureListener((Activity) context, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("TEST", "" + e.getMessage());
                }
            });
        }
    }

    private void getDataFromGeoCoder(Location location){
        try {
            List<Address> localeList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            addressTextView.setText("Address: " + localeList.get(0).getAddressLine(0));
            localityTextView.setText("" + localeList.get(0).getLocality());
            countryTextView.setText("Country: " + localeList.get(0).getCountryName());
        } catch (IOException e) {
            addressTextView.setText("Address: " + "Not Found!");
            localityTextView.setText("" + "Not Found!");
            countryTextView.setText("Country: " + "Not Found!");
        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }
}