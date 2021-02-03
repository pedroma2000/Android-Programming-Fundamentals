package pt.ipp.estg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class NewActivity extends AppCompatActivity {

    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        mTextView2 = findViewById(R.id.textView2);

        mTextView2.setText(getIntent().getStringExtra("KEY"));
        Log.d("NEW_ACTIVITY","onCreate()");
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MAIN_ACTIVITY","onStart()");
    }
}