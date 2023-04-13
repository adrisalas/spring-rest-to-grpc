package com.example.client.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteGrpcClient;

    public Note findById(int id) {
        return noteGrpcClient.findById(id);
    }

    public Collection<Note> findAll() {
        return noteGrpcClient.findAll();
    }

    public void save(Note note) {
        noteGrpcClient.save(note);
    }

    public void delete(int id) {
        noteGrpcClient.delete(id);
    }
}
