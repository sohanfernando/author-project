package com.example.book_project.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorRequestDTO {
    private String name;
    private List<Long> bookIds;
}
