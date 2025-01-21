package com.example.book_project.service.impl;

import com.example.book_project.dto.request.AuthorRequestDTO;
import com.example.book_project.dto.response.AuthorResponseDTO;
import com.example.book_project.model.Author;
import com.example.book_project.model.Book;
import com.example.book_project.repository.AuthorRepository;
import com.example.book_project.repository.BookRepository;
import com.example.book_project.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO){
        Author author = new Author();
        author.setName(authorRequestDTO.getName());

        if(authorRequestDTO.getBookIds() != null && !authorRequestDTO.getBookIds().isEmpty()){
            List<Book> books = bookRepository.findAllById(authorRequestDTO.getBookIds());
            author.setBooks(books);
        }

        Author savedAuthor = authorRepository.save(author);
        List<Long> bookIds = savedAuthor.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        return new AuthorResponseDTO(savedAuthor.getId(), savedAuthor.getName(), bookIds);
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors(){
        return authorRepository.findAll()
                .stream()
                .map(author -> new AuthorResponseDTO(author.getId(), author.getName(), author.getBooks().stream().map(Book::getId).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        List<Long> bookIds = author.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        return new AuthorResponseDTO(author.getId(), author.getName(), bookIds);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

}
