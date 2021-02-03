package pt.ipp.estg.simplefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import pt.ipp.estg.simplefragment.fragments.Fragment_1;
import pt.ipp.estg.simplefragment.fragments.Fragment_2;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements MyInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment_1 f1 = new Fragment_1();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, f1,"Home");
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void sendTextToFragment(String text) {
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