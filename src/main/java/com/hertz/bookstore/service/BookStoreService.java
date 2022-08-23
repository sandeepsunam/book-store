package com.hertz.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hertz.bookstore.entity.Author;
import com.hertz.bookstore.entity.BookStore;
import com.hertz.bookstore.entity.Category;
import com.hertz.bookstore.repository.BookStoreRepository;
import com.hertz.bookstore.request.model.BookRequest;

@Component
public class BookStoreService {
	
	@Autowired
	private BookStoreRepository bookStoreRepo;
	
	@Transactional
	public void addBook(final BookRequest request) {
		
		BookStore bookStore = bookStoreRepo.findByName(request.getBook());
		
		//book already present
		if(bookStore != null) {
			throw new RuntimeException(" Book is already in the book store! Cannot add");
		}
		
		//create and prepare new book
		bookStore = new BookStore();
		bookStore.setName(request.getBook());
		
		final Author author = new Author();
		author.setName(request.getAuthor());
		bookStore.setAuthor(author);
		
		final List<Category> categoryList = request.getCategory().stream()
							 .map( x -> new Category(x))
							 .collect(Collectors.toList());
		
		bookStore.setCategory(categoryList);	
		
		//save
		bookStoreRepo.save(bookStore);
	}
	
	@Transactional
	public void updateBook(final BookRequest request, final Long id) {
		
		if(bookStoreRepo.findById(id).isPresent()) {
			
			final BookStore bookStore = new BookStore();
			bookStore.setName(request.getBook());
			
			final Author author = new Author();
			author.setName(request.getAuthor());
			bookStore.setAuthor(author);
			
			final List<Category> categoryList = request.getCategory().stream()
								 .map( x -> new Category(x))
								 .collect(Collectors.toList());
			
			bookStore.setCategory(categoryList);		
			bookStore.setId(id);
			bookStoreRepo.save(bookStore);
		} else {
			throw new RuntimeException("book is not present in the store");
		}
		
	}
	
	public List<BookStore> getALlBooks() {
		
		return bookStoreRepo.findAll();
	}

	public BookStore getBook(final Long id) {
		
		return bookStoreRepo.findById(id).get();
	}

	public void deleteBook(final Long id) {
		
		if(bookStoreRepo.findById(id).isPresent()) {
			bookStoreRepo.deleteById(id);
		} else {
			throw new RuntimeException("book is not present in the store");
		}
	}

}
