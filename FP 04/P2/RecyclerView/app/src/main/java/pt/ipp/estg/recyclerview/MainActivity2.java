package pt.ipp.estg.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity2 extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myTextView = findViewById(R.id.textView2);

        Contacto contact = (Contacto) getIntent().getSerializableExtra("CONTACT");
        myTextView.setText(contact.getNome());

        Log.d("ACTIVITY_2","onCreate()");
    }
}