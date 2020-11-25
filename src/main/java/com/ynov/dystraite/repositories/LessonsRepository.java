package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Lessons;


@Repository
public interface LessonsRepository extends JpaRepository<Lessons, Integer>{
	@Query(
			  value = "SELECT * FROM lessons order by created_at desc limit ?1",
			  nativeQuery = true)
	  List<Lessons> findLastCreated(int limit);
}