package pt.ipp.estg.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.ipp.estg.toolbar.adapters.ContactAdapter;
import pt.ipp.estg.toolbar.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecycler;
    private ContactAdapter myAdapter;
    private View root;


    private Toolbar myToolBar;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        List<Contact> contacts = createContactList(200);

        myAdapter = new ContactAdapter(this, contacts);


        myRecycler = findViewById(R.id.recycler_view);
        root = myRecycler.getRootView();
        myRecycler.setAdapter(myAdapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));
        myRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        myToolBar = (Toolbar) findViewById(R.id.toolbar);
        myToolBar.setTitle("Lista de Contactos");
        setSupportActionBar(myToolBar);

        Log.d("MAIN_ACTIVITY", "onCreate()");
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MAIN_ACTIVITY","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        String color = mSettings.getString("COLOR", "WHITE");
        System.out.println("RESULT COLOR ->" + color);
        changeBackground(color);
        Log.d("MAIN_ACTIVITY", "onResume()");
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Contact> createContactList(int size) {
        Random rd = new Random();
        List<Contact> tmp = new ArrayList<>(size);
        tmp.add(0, new Contact("Person " + 0, true));
        for (int i = 1; i < size; i++) {
            tmp.add(i, new Contact("Person " + i, rd.nextBoolean()));
        }
        return tmp;
    }

    private void changeBackground(String color) {
        if (color.equals("WHITE")) {
            root.setBackgroundColor(Color.WHITE);
        } else if (color.equals("YELLOW")) {
            root.setBackgroundColor(Color.YELLOW);
        } else if (color.equals("RED")) {
            root.setBackgroundColor(Color.RED);
        }
        Log.d("MAIN_ACTIVITY", "changeBackground()");
    }
}