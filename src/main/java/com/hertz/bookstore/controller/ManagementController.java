package com.hertz.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hertz.bookstore.entity.BookStore;
import com.hertz.bookstore.entity.Member;
import com.hertz.bookstore.repository.BookStoreRepository;
import com.hertz.bookstore.repository.MemberRepository;
import com.hertz.bookstore.request.model.MemberRequest;

@RestController
@RequestMapping("/members")
public class ManagementController {
	
	@Autowired
	private MemberRepository memberRepo;
	
	
	@Autowired
	private BookStoreRepository booksRepo;
	
	@GetMapping 
	public List<Member> getAllMembers() {
		return memberRepo.findAll();
	}
	
	@GetMapping("/{id}") 
	public Member getMember(@PathVariable Long id) {
		return memberRepo.findById(id).get();
	}
		
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createMembers(@RequestBody final MemberRequest request) {
		
		Member member = memberRepo.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
		
		if(member == null) {
			member = new Member();
			BeanUtils.copyProperties(request, member);
			memberRepo.save(member);
		} else {
			throw new RuntimeException("Member is already registered!");
		}
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/{memberId}/book/{id}") 
	public void orderBook(@PathVariable final Long memberId,
						  @PathVariable final Long id) {
		
		final Optional<Member> storeMember = memberRepo.findById(memberId);
		
		if(storeMember.isPresent()) {
			
			final Optional<BookStore> bookInStore = booksRepo.findById(id);
			
			if(bookInStore.isPresent()) {
				
				if(bookInStore.get().getQuantity() != 0 || storeMember.get().getBooks().size() <= 3) {
					BookStore book = bookInStore.get();
					Member member = storeMember.get();
					
					member.getBooks().add(book);
					book.setQuantity(0);
					memberRepo.save(member);
					booksRepo.save(book);
				} else {
					throw new RuntimeException("Book is not available to borrow or you have reach the maximum limit!");
				}
			} else {
				throw new RuntimeException("Book is not available!");
			}
			
		} else {
			throw new RuntimeException("Member is not registered!");
		}
	}
	
	
	@PutMapping("/{memberId}/return-book") 
	public void returnBook(@PathVariable Long memberId) {
		final Member member = memberRepo.findById(memberId).get();
		
		member.getBooks()
				.forEach(book -> {
					BookStore bs = booksRepo.findById(book.getId()).get();
					bs.setQuantity(1);
					booksRepo.save(bs);
				});
		
		member.setBooks(null);
		memberRepo.save(member);
	}

}
