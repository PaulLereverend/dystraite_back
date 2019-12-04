package com.ynov.dystraite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Cours;


@Repository
public interface CoursRepository extends JpaRepository<Cours, Integer>{
}