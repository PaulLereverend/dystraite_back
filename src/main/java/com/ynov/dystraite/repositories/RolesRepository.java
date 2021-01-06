package com.ynov.dystraite.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.Roles;
import com.ynov.dystraite.models.EnumRoles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByName(EnumRoles name);

}
