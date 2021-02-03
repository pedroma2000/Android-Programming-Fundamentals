package pt.ipp.estg.hugearraylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity {

    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Contact> ContactList = makeList(500);

        myListView = findViewById(R.id.itemListView);
        ListAdapter customAdapter = new MyListAdapter(this, R.layout.row_layout, ContactList);
        myListView.setAdapter(customAdapter);
    }

    private List<Contact> makeList(int size){
        List<Contact> tmp = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            tmp.add(i, new Contact("Person" + i));
        }
        return tmp;
    }
}