package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	public Users findByEmail(String email);
	
	@Query(value = "SELECT *, 111.111 *\n" + 
			"    DEGREES(ACOS(LEAST(1.0, COS(RADIANS(?1))\n" + 
			"         * COS(RADIANS(latitude))\n" + 
			"         * COS(RADIANS(?2 - longitude))\n" + 
			"         + SIN(RADIANS(?1))\n" + 
			"         * SIN(RADIANS(latitude))))) AS distance FROM users WHERE role = 'speech_therapist' HAVING distance < 25 ORDER BY distance LIMIT 0 , 20",
			nativeQuery = true)
	public List<Users> findNearest(long lat, long longi);
}