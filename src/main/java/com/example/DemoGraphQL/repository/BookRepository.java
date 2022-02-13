package com.example.DemoGraphQL.repository;

import com.example.DemoGraphQL.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>
{
}
