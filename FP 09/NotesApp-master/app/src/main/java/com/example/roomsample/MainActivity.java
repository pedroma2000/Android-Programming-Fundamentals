package com.example.roomsample;

import android.os.Bundle;

import com.example.roomsample.Adapters.NotesAdapter;
import com.example.roomsample.Database.NotesDatabase;
import com.example.roomsample.model.Note;
import com.example.roomsample.model.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
public class MainActivity extends AppCompatActivity implements NoteDialog.NoteListenerInterface {

    NotesAdapter mNotesAdapter;
    RecyclerView notesList;
    private NoteViewModel mNotesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notesList = findViewById(R.id.reciclerview);
        notesList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        notesList.addItemDecoration(itemDecoration);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                NoteDialog newFragment = new NoteDialog();
                newFragment.show(fragmentManager, "dialog");

            }
        });

        mNotesViewModel = new ViewModelProvider
                (this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(NoteViewModel.class);
        mNotesAdapter = new NotesAdapter(this, mNotesViewModel);
        notesList.setAdapter(mNotesAdapter);

        mNotesViewModel.getAllNotes().observe(this, notes -> {
            mNotesAdapter.setNotes(notes);
            mNotesAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void addNote(String title, String description) {
        Note n = new Note(title, description);
        mNotesViewModel.insert(n);
    }
}
