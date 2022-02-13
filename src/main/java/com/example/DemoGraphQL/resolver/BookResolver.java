package com.example.DemoGraphQL.resolver;

import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLResolver;

public class BookResolver implements GraphQLResolver<Book>
{
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository)
    {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book)
    {
        return authorRepository.findById(book.getAuthor()
                                             .getId())
                               .orElseThrow(null);
    }
}
