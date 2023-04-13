package com.example.client.feign;

import com.example.client.business.Note;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteRepository implements com.example.client.business.NoteRepository {
    private final NoteFeignClient feign;

    @Override
    public Note findById(int id) {
        log.error("Not implemented!");
        return null;
    }

    @Override
    public Collection<Note> findAll() {
        return feign.findAll();
    }

    @Override
    public void save(Note note) {
        log.error("Not implemented!");
    }

    @Override
    public void delete(int id) {
        log.error("Not implemented!");
    }
}
