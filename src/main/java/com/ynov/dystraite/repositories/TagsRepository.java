package com.ynov.dystraite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer>{
}