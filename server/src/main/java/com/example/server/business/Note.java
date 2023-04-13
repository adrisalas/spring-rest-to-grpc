package com.example.server.business;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Getter
@Builder
public class Note {
    @With
    private final int id;
    private final String message;
}
