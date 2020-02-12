package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Cours;


@Repository
public interface CoursRepository extends JpaRepository<Cours, Integer>{
	@Query(
			  value = "SELECT * FROM cours order by created_at desc limit ?1", 
			  nativeQuery = true)
	  List<Cours> findLastCreated(int limit);
}