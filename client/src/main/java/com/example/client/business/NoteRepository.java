package com.example.client.business;

import java.util.Collection;

public interface NoteRepository {
    Note findById(int id);

    Collection<Note> findAll();

    void save(Note note);

    void delete(int id);
}
