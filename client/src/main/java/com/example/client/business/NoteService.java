package com.example.client.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note findById(int id) {
        return noteRepository.findById(id);
    }

    public Collection<Note> findAll() {
        return noteRepository.findAll();
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public void delete(int id) {
        noteRepository.delete(id);
    }
}
