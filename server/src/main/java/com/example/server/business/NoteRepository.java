package com.example.server.business;

import java.util.Collection;
import java.util.Optional;

public interface NoteRepository {
    Optional<Note> findById(int id);

    Collection<Note> findAll();

    void save(Note note);

    void delete(int id);
}
