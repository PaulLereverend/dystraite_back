package com.ynov.dystraite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer>{
}
