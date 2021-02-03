package pt.ipp.estg.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecycler;
    private ContactAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Contacto> contacts = createContactList(200);

        myAdapter = new ContactAdapter(this, contacts);

        myRecycler = findViewById(R.id.recycler1);
        myRecycler.setAdapter(myAdapter);

        myRecycler.setLayoutManager(new LinearLayoutManager(this));

        myRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Log.d("MAIN_ACTIVITY","onCreate()");
    }

    private List<Contacto> createContactList(int size){
        Random rd = new Random();
        List<Contacto> tmp = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            tmp.add(i, new Contacto("Person" + i, rd.nextBoolean()));
            //myAdapter.notifyItemInserted(i);
        }
        return tmp;
    }
}