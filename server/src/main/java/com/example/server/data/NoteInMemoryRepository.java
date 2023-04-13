package com.example.server.data;

import com.example.server.business.Note;
import com.example.server.business.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NoteInMemoryRepository implements NoteRepository {

    private final List<Note> notes = new ArrayList<>();

    @Override
    public Optional<Note> findById(int id) {
        int position = findNotePosition(id);

        if (position == -1) {
            return Optional.empty();
        }

        return Optional.of(notes.get(position));
    }

    private int findNotePosition(int id) {
        int position = -1;

        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                position = i;
                break;
            }
        }

        return position;
    }

    @Override
    public Collection<Note> findAll() {
        return notes;
    }

    @Override
    public void save(Note note) {
        notes.add(note);
    }

    @Override
    public void delete(int id) {
        int position = findNotePosition(id);

        if (position >= 0) {
            notes.remove(position);
        }
    }
}
