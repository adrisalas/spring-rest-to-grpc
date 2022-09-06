package com.example.server.data;

import com.example.server.business.Note;
import com.example.server.business.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class NoteSQLRepository implements NoteRepository {

    private final NoteCrudRepository noteCrudRepository;

    @Override
    public Optional<Note> findById(int id) {
        return noteCrudRepository.findById(id);
    }

    @Override
    public Collection<Note> findAll() {
        return noteCrudRepository.findAll();
    }

    @Override
    public void save(Note note) {
        noteCrudRepository.save(note);
    }

    @Override
    public void delete(int id) {
        noteCrudRepository.deleteById(id);
    }
}
