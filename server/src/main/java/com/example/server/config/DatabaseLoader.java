package com.example.server.config;

import com.example.server.business.Note;
import com.example.server.business.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(NoteRepository noteRepository) {
        return args -> {
            noteRepository.save(Note.builder()
                                    .message("First message")
                                    .build());
            noteRepository.save(Note.builder()
                                    .message("Second message")
                                    .build());
        };
    }

}
