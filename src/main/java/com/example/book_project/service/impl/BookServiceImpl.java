package com.example.book_project.service.impl;

import com.example.book_project.dto.request.BookRequestDTO;
import com.example.book_project.dto.response.BookResponseDTO;
import com.example.book_project.model.Book;
import com.example.book_project.repository.AuthorRepository;
import com.example.book_project.repository.BookRepository;
import com.example.book_project.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO){
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        Book savedBook = bookRepository.save(book);
        return new BookResponseDTO(savedBook.getId(), savedBook.getTitle());
    }

    @Override
    public List<BookResponseDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(book -> new BookResponseDTO(book.getId(), book.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return new BookResponseDTO(book.getId(), book.getTitle());
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
