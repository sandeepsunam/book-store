package com.hertz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hertz.bookstore.entity.BookStore;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {

	BookStore findByName(String name);
}
