package com.example.book_project.service;

import com.example.book_project.dto.request.AuthorRequestDTO;
import com.example.book_project.dto.response.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    void deleteAuthorById(Long id);
}
