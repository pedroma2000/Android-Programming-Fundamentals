package pt.ipp.estg.toolbarandpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pt.ipp.estg.toolbarandpreferences.DataBase.MyDB;
import pt.ipp.estg.toolbarandpreferences.adapters.ContactAdapter;
import pt.ipp.estg.toolbarandpreferences.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static  MyDB myDB;

    private Toolbar myToolBar;
    private RecyclerView recyclerList;
    private ContactAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = Room.databaseBuilder(getApplicationContext(), MyDB.class, "MyDB").build();

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
        myAdapter = new ContactAdapter(this);

        recyclerList = findViewById(R.id.contactRecycler);
        recyclerList.setAdapter(myAdapter);
        recyclerList.setLayoutManager(new LinearLayoutManager(this));

        recyclerList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_contact:
                Intent i = new Intent(this, AddContact.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}



