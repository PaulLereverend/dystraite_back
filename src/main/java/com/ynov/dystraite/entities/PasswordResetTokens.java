package com.ynov.dystraite.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class PasswordResetTokens implements Serializable {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_email", referencedColumnName = "email")
    private Users user;

    private LocalDateTime expiryDate;

    public PasswordResetTokens(String token, Users user, LocalDateTime expiryDate) {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }
}

