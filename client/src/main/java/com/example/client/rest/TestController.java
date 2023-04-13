package com.example.client.rest;

import com.example.client.business.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final NoteRepository noteRepository;
    private final NoteRepository noteGrpcClient;

    @GetMapping
    public void test() {
        long startFeign = System.currentTimeMillis();
        var feign = noteRepository.findAll();
        long stopFeign = System.currentTimeMillis();
        long timeFeign = stopFeign - startFeign;
        log.info("Feign took: " + timeFeign + "ms");

        long startGrpc = System.currentTimeMillis();
        var grpc = noteGrpcClient.findAll();
        long stopGrpc = System.currentTimeMillis();
        long timeGrpc = stopGrpc - startGrpc;
        log.info("Grpc took: " + timeGrpc + "ms");

    }
}
