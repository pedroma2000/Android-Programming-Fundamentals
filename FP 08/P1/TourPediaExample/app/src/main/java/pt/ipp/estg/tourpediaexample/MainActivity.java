package pt.ipp.estg.tourpediaexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar myToolBar;
    private Button mButton;
    private EditText insertLocation;
    private EditText insertName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertLocation = findViewById(R.id.insert_location);
        insertName = findViewById(R.id.insert_name);

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);

        myToolBar = findViewById(R.id.toolbar);
        myToolBar.setTitle("Escolher Localização");
        setSupportActionBar(myToolBar);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MAIN_ACTIVITY","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MAIN_ACTIVITY","onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MAIN_ACTIVITY","onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MAIN_ACTIVITY","onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("MAIN_ACTIVITY","onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MAIN_ACTIVITY","onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("MAIN_ACTIVITY", "onCreateOptionsMenu()");
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, LocationsActivity.class);
        i.putExtra("Location", insertLocation.getText().toString());
        i.putExtra("Name", insertName.getText().toString());
        this.startActivity(i);
    }
}