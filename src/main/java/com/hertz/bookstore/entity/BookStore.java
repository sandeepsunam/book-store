package com.hertz.bookstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BK_SEQ")
	private Long id;
	private String name;
	private int quantity = 1;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Author author;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	private List<Category> category;
}
