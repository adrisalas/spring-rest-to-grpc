package com.example.server.data;

import com.example.server.business.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

interface NoteCrudRepository extends CrudRepository<Note, Integer> {
    @Override
    Collection<Note> findAll();
}
