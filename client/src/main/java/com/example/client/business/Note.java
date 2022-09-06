package com.example.client.business;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Note {
    private int id;
    private String message;
}
