package com.example.DemoGraphQL.controller;

import com.example.DemoGraphQL.exception.BookNotFoundException;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.repository.AuthorRepository;
import com.example.DemoGraphQL.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @MutationMapping
    public Book newBook(@Argument String title, @Argument String isbn, @Argument Integer pageCount, @Argument Long authorId) {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);
        bookRepository.save(book);
        return book;
    }

    @MutationMapping
    public Book updateBookPageCount(@Argument Integer pageCount, @Argument Long id) {
        Optional<Book> opt = bookRepository.findById(id);
        if (opt.isPresent()) {
            Book book = opt.get();
            book.setPageCount(pageCount);
            bookRepository.save(book);
            return book;
        }
        throw new BookNotFoundException("The book to be updated was found", id);
    }

    @QueryMapping
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public long countBooks() {
        return bookRepository.count();
    }

    @SchemaMapping
    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor()
                        .getId())
                .orElseThrow(null);
    }

    @MutationMapping
    public boolean deleteBook(@Argument Long id) {
        bookRepository.deleteById(id);
        return true;
    }
}
