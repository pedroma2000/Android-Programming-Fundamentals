package pt.ipp.estg.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import pt.ipp.estg.toolbar.Interfaces.IDialogReset;
import pt.ipp.estg.toolbar.dialogs.ResetDialog;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class SettingsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        IDialogReset {

    private Toolbar myToolBar;
    private RadioGroup colorRadios;
    private SharedPreferences mSettings;
    SharedPreferences.Editor mEditor;
    private ResetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        colorRadios = findViewById(R.id.colorRadios);
        colorRadios.setOnCheckedChangeListener(this);

        myToolBar = (Toolbar) findViewById(R.id.toolbar);
        myToolBar.setTitle("Definições");
        setSupportActionBar(myToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("SETTINGS_ACTIVITY", "onCreate()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("SETTINGS_ACTIVITY","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SETTINGS_ACTIVITY", "onResume()");
    }


    @Override
    protected void onPause(){
        super.onPause();
        Log.d("SETTINGS_ACTIVITY","onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("SETTINGS_ACTIVITY","onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("SETTINGS_ACTIVITY","onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SETTINGS_ACTIVITY","onDestroy()");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu:
                dialog =  new ResetDialog();
                dialog.show(getSupportFragmentManager(), "RESET_DIALOG");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mEditor = mSettings.edit();
        if(checkedId == R.id.radioWhite){
            mEditor.putString("COLOR", "WHITE");
        } else if(checkedId == R.id.radioYellow){
            mEditor.putString("COLOR", "YELLOW");
        } else if(checkedId == R.id.radioRed){
            mEditor.putString("COLOR", "RED");
        } mEditor.commit();
        Log.d("SETTINGS_ACTIVITY","SharedPreferences COMMIT!");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mEditor = mSettings.edit();
        mEditor.putString("COLOR", "WHITE");
        mEditor.commit();
        //mEditor.clear().commit();
        dialog.dismiss();
        Log.d("SETTINGS_ACTIVITY","RESET PREFERENCES!");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }
}