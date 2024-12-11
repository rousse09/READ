package com.read.biblioteca.repository;

import com.read.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
