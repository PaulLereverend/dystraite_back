package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer>{
	@Query(
			  value = "SELECT * FROM livre order by created_at desc limit ?1", 
			  nativeQuery = true)
	  List<Livre> findLastCreated(int limit);
}
