package com.example.livedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    private MasterFragment f1;
    private DetailsFragment f2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f1 = new MasterFragment();
        f2 = new DetailsFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, f1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment2, f2).commit();
    }
}
