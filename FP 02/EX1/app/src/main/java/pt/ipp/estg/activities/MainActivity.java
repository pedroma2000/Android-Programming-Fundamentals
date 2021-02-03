package pt.ipp.estg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditText;
    private Button mButtonToast;
    private Button mButtonNewView;

    //Perguntar melhor maneira de fazer logica de botoes com intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editTextTextPersonName3);
        mButtonToast = findViewById(R.id.button);
        mButtonNewView = findViewById(R.id.button2);

        mButtonToast.setOnClickListener(this);

        mButtonNewView.setOnClickListener(this);


        Log.d("NEW_ACTIVITY","onCreate()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MAIN_ACTIVITY","onStart()");
    }

    @Override
    protected void onResume(){
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
    public void onClick(View view) {
        if(view.getId() == R.id.button){
            int duration = Toast.LENGTH_SHORT;
            String text = mEditText.getText().toString();
            Toast toast = Toast.makeText(this, text, duration);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
            toast.show();
        }

        if(view.getId() == R.id.button2) {
            openNewActivityWithText();
        }

    }

    private void openNewActivityWithText(){
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("KEY", mEditText.getText().toString());
        startActivity(intent);
    }


}