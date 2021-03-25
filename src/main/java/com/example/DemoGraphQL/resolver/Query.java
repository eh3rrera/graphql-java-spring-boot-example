package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.repository.AuthorRepository;
import com.example.DemoGraphQL.repository.BookRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver
{
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAllBooks()
    {
        return bookRepository.findAll();
    }

    public long countBooks()
    {
        return bookRepository.count();
    }

    public Iterable<Author> findAllAuthors()
    {
        return authorRepository.findAll();
    }

    public long countAuthors()
    {
        return authorRepository.count();
    }
}
