package com.example.server.config;

import com.example.server.business.Note;
import com.example.server.business.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
@Slf4j
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(NoteRepository noteRepository) {
        return args -> {
            int numberOfNotes = 1_000_000;
            log.info("Generating " + numberOfNotes + " notes");
            for (int i = 0; i < numberOfNotes; i++) {
                noteRepository.save(Note.builder()
                                        .id(i)
                                        .message(randomString())
                                        .build());
            }
            log.info("Data generated!");
        };
    }

    private static String randomString() {
        String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 150; i++) {
            sb.append(words.charAt(random.nextInt(words.length())));
        }
        return sb.toString();
    }

}
