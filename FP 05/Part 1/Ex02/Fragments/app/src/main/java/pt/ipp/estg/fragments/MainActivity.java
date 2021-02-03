package pt.ipp.estg.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import pt.ipp.estg.fragments.entities.City;
import pt.ipp.estg.fragments.fragments.ListFragment;
import pt.ipp.estg.fragments.fragments.DetailsFragment;

public class MainActivity extends AppCompatActivity implements MyInterface {

    private ListFragment f1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            f1 = new ListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, f1).commit();
        }
    }

    @Override
    public void ReplaceDetails(City city) {
        DetailsFragment newFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);

        if(newFragment != null) {
            newFragment.update(city);
        } else {
            newFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("CITY", city);
            newFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

}