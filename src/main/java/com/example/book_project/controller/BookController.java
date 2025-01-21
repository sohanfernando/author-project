package com.example.book_project.controller;

import com.example.book_project.dto.request.BookRequestDTO;
import com.example.book_project.dto.response.BookResponseDTO;
import com.example.book_project.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.ok(bookService.createBook(bookRequestDTO));
    }
}
