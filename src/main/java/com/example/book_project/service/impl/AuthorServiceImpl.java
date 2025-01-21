package com.example.book_project.service.impl;

import com.example.book_project.dto.request.AuthorRequestDTO;
import com.example.book_project.dto.response.AuthorResponseDTO;
import com.example.book_project.model.Author;
import com.example.book_project.repository.AuthorRepository;
import com.example.book_project.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO){
        Author author = new Author();
        author.setName(authorRequestDTO.getName());
        Author savedAuthor = authorRepository.save(author);
        return new AuthorResponseDTO(savedAuthor.getId(), savedAuthor.getName());
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors(){
        return authorRepository.findAll()
                .stream()
                .map(author -> new AuthorResponseDTO(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return new AuthorResponseDTO(author.getId(), author.getName());
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

}
