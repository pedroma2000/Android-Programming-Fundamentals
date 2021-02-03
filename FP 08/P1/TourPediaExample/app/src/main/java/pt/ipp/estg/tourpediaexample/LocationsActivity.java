package pt.ipp.estg.tourpediaexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import pt.ipp.estg.tourpediaexample.Fragments.DetailsFragment;
import pt.ipp.estg.tourpediaexample.Fragments.ListFragment;
import pt.ipp.estg.tourpediaexample.Models.POI;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class LocationsActivity extends AppCompatActivity {

    private Toolbar myToolBar;
    private ListFragment f1;
    private String location;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        myToolBar = findViewById(R.id.toolbar);
        myToolBar.setTitle("Lista de localizações");
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        location = i.getStringExtra("Location");
        name = i.getStringExtra("Name");

        f1 = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("LOCATION", location);
        bundle.putString("NAME", name);
        f1.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, f1)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("MAIN_ACTIVITY", "onCreateOptionsMenu()");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ReplaceDetails(POI poi) {
        DetailsFragment newFragment;

        newFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("POI", poi);
        newFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null)
                .commit();
    }
}