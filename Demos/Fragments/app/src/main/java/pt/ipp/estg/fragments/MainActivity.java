package pt.ipp.estg.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import pt.ipp.estg.fragments.fragments.Fragment_1;
import pt.ipp.estg.fragments.fragments.Fragment_2;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements CustomInterface{

    FrameLayout frameLayout;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //frameLayout = findViewById(R.id.fragment_container);

        //b1 = findViewById(R.id.button2);

        Fragment_1 f1 = new Fragment_1();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, f1,"Home");
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void myMethod(String text) {
        Fragment_2 f2 = new Fragment_2();

        Bundle b = new Bundle();
        b.putString("VALUE", text);
        f2.setArguments(b);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, f2,"details");
        ft.addToBackStack(null);
        ft.commit();
    }
}