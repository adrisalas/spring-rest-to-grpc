package com.example.server.rest;

import com.example.server.business.Note;
import com.example.server.business.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteRestController {
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
        noteService.save(note.withId(0));
    }

    @PutMapping("/{id}")
    public void save(@PathVariable int id, @RequestBody Note note) {
        if (note.getId() != 0) {
            noteService.save(note.withId(id));
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        noteService.delete(id);
    }
}
