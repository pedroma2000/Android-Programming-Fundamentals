package pt.ipp.estg.toolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import pt.ipp.estg.toolbar.models.Contact;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class DetailsActivity extends AppCompatActivity {
    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        myTextView = findViewById(R.id.texto_view);

        Contact contact = (Contact) getIntent().getSerializableExtra("CONTACT");
        myTextView.setText(contact.getNome());

        Log.d("DETAILS_ACTIVITY","onCreate()");
    }
}