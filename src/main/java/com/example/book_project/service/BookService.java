package com.example.book_project.service;

import com.example.book_project.dto.request.AuthorRequestDTO;
import com.example.book_project.dto.request.BookRequestDTO;
import com.example.book_project.dto.response.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Long id);
    void deleteBookById(Long id);
}
