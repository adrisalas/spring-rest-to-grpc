package com.example.client.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {
    private int id;
    private String message;
}
