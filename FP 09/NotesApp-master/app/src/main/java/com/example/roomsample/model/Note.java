package com.example.roomsample.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 * @author Pedro Machado pedroma2000
 */
@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
