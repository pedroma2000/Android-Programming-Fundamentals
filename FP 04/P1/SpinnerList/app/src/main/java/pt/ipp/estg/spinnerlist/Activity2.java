package pt.ipp.estg.spinnerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class Activity2 extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        myTextView = findViewById(R.id.textView);
        
        myTextView.setText(getIntent().getStringExtra("COUNTRY"));
    }
}