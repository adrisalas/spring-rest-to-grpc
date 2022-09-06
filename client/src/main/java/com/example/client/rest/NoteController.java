package com.example.client.rest;

import com.example.client.business.Note;
import com.example.client.business.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public Collection<Note> findAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public Note findById(@PathVariable int id) {
        return noteService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Note note) {
        note.setId(0);
        noteService.save(note);
    }

    @PutMapping("/{id}")
    public void save(@PathVariable int id, @RequestBody Note note) {
        if (note.getId() != 0) {
            note.setId(id);
            noteService.save(note);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        noteService.delete(id);
    }
}
