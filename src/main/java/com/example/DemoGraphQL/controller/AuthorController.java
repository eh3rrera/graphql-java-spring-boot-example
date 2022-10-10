package com.example.DemoGraphQL.controller;

import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.repository.AuthorRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @MutationMapping
    public Author newAuthor(@Argument String firstName, @Argument String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return author;
    }

    @QueryMapping
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    public long countAuthors() {
        return authorRepository.count();
    }
}
