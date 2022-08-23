package com.hertz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hertz.bookstore.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByFirstNameAndLastName(String firstName, String lastName);
}
