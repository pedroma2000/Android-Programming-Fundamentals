package pt.ipp.estg.soundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Intent startService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        startService = new Intent(this, AudioService.class);
        startService(startService);
    }

    @Override
    public void onClick(View v) {
        stopService(startService);
    }
}