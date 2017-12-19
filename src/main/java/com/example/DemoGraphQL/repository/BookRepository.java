package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
