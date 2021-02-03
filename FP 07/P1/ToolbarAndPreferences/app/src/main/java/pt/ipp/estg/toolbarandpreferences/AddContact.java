package pt.ipp.estg.toolbarandpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ipp.estg.toolbarandpreferences.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class AddContact extends AppCompatActivity implements View.OnClickListener {

    private Toolbar myToolBar;
    private TextView insertName;
    private TextView insertNumber;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        insertName = findViewById(R.id.insert_name);
        insertNumber = findViewById(R.id.insert_phone);

        deleteButton = findViewById(R.id.insert_button);
        deleteButton.setOnClickListener(this);

        myToolBar = (Toolbar) findViewById(R.id.toolbar);
        myToolBar.setTitle("Inserir Contacto");
        setSupportActionBar(myToolBar);
        myToolBar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("ADDCONTACT_ACTIVITY","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ADDCONTACT_ACTIVITY", "onResume()");
    }


    @Override
    protected void onPause(){
        super.onPause();
        Log.d("ADDCONTACT_ACTIVITY","onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("ADDCONTACT_ACTIVITY","onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("ADDCONTACT_ACTIVITY","onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("ADDCONTACT_ACTIVITY","onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addcontact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==android.R.id.home){
            onBackPressed();
        }
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        MainActivity.databaseWriteExecutor.execute(() -> {
            MainActivity.myDB.daoAccess().insertContacts(new Contact(insertName.getText().toString(),
                    insertNumber.getText().toString()));
        });
    }
}