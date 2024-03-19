package com.example.parsing.controller;

import com.example.parsing.service.ParsingUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {
    @Autowired
    private final ParsingUrlService parsingUrlService;


    @PostMapping
    private ResponseEntity<?> parsingInfo(@RequestBody String url) {
        if (url != null && !url.isBlank()) {
            parsingUrlService.parsingUrl(url);
        }
            return ResponseEntity.badRequest().build();
    }
}
