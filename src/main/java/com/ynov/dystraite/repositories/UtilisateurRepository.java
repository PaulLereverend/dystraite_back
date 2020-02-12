package com.ynov.dystraite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	public Utilisateur findByEmail(String email);
	
	@Query(value = "SELECT *, 111.111 *\n" + 
			"    DEGREES(ACOS(LEAST(1.0, COS(RADIANS(?1))\n" + 
			"         * COS(RADIANS(latitude))\n" + 
			"         * COS(RADIANS(?2 - longitude))\n" + 
			"         + SIN(RADIANS(?1))\n" + 
			"         * SIN(RADIANS(latitude))))) AS distance FROM utilisateur WHERE role = 'orthophoniste' HAVING distance < 25 ORDER BY distance LIMIT 0 , 20",
			nativeQuery = true)
	public List<Utilisateur> findNearest(long lat, long longi);
}