package com.example.book_project.controller;

import com.example.book_project.dto.request.AuthorRequestDTO;
import com.example.book_project.dto.response.AuthorResponseDTO;
import com.example.book_project.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
        return ResponseEntity.ok(authorService.createAuthor(authorRequestDTO));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
