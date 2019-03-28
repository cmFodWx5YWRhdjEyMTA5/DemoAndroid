package com.example.kuldeep.login.note;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM "+ Constants.TABLE_NAME_NOTE)
    List<Note> getNotes();

    @Insert
    long insertNote(Note note);

    @Update
    void updateNote(Note repos);

    @Delete
    void deleteNote(Note note);

    @Delete
    void deleteNotes(Note... note);

}
