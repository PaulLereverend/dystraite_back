package com.ynov.dystraite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ynov.dystraite.entities.PasswordResetTokens;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokens, Integer>{
    public Optional<PasswordResetTokens> findByToken(String token);
}