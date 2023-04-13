package com.example.client.feign;

import com.example.client.business.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "notes", url = "http://${rest-to-database.address}:${rest-to-database.port}/notes")
interface NoteFeignClient {
    @GetMapping("/")
    List<Note> findAll();
}
