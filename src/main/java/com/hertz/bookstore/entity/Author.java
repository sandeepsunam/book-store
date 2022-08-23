package com.hertz.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_SEQ")
	private Long id;
	private String name;
}
