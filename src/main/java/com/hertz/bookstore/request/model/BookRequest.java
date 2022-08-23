package com.hertz.bookstore.request.model;

import java.util.List;

import lombok.Data;

@Data
public class BookRequest {
	
	private String book;
	private String author;
	private List<String> category;

}
