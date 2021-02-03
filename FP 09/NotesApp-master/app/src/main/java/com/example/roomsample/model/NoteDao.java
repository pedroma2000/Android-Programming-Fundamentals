package com.example.roomsample.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 *
 * @author Pedro Machado pedroma2000
 */
@Dao
public interface NoteDao {

    @Insert
    public void insertNote(Note ... notes);

    @Delete
    public void deleteNote(Note ... notes);

    @Query("SELECT * FROM Note")
    public LiveData<List<Note>> loadAllNotes();

    @Query("SELECT COUNT(*) FROM Note")
    int getCount();

}
