package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer>{
	@Query(
			  value = "SELECT * FROM books order by created_at desc limit ?1",
			  nativeQuery = true)
	  List<Books> findLastCreated(int limit);
}
