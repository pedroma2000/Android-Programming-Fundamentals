package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mResultado;
    private EditText mEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mResultado = findViewById(R.id.textView6);
        mEditText = findViewById(R.id.editTextTextPersonName3);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(this);

        Log.d("MAIN_ACTIVITY","onCreate()");
    }


    @Override
    public void onClick(View v) {
        if(Integer.parseInt(mEditText.getText().toString()) == 5){
            mResultado.setText("O Resultado está correcto!");
        } else {
            mResultado.setText("O Resultado está errado!");
        }
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
}