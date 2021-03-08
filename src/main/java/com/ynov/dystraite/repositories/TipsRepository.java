package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Tips;

@Repository
public interface TipsRepository extends JpaRepository<Tips, Integer>{
	@Query(
			  value = "SELECT * FROM tips order by created_at desc limit ?1",
			  nativeQuery = true)
	  List<Tips> findLastCreated(int limit);
}
