package com.hertz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hertz.bookstore.entity.BookStore;
import com.hertz.bookstore.request.model.BookRequest;
import com.hertz.bookstore.service.BookStoreService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BookStoreService bookStoreService;

	@GetMapping
	public List<BookStore> getAllbooks() {
		return bookStoreService.getALlBooks();
	}
	
	
	@GetMapping("/{id}")
	public BookStore getbook(@PathVariable final Long id) {
		return bookStoreService.getBook(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public void addBook(@RequestBody final BookRequest request) {
		bookStoreService.addBook(request);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void updateBook(@RequestBody final BookRequest request, @PathVariable final Long id) {
		bookStoreService.updateBook(request, id);
		
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable final Long id) {
		bookStoreService.deleteBook(id);
		
	}
	
}
