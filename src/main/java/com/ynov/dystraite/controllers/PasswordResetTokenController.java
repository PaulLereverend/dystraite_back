package com.ynov.dystraite.controllers;

import com.ynov.dystraite.exceptions.PasswordResetTokenExpiredException;
import com.ynov.dystraite.exceptions.PasswordResetTokenNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.exceptions.UserNotFoundException;
import com.ynov.dystraite.services.UsersService;
import com.ynov.dystraite.repositories.UsersRepository;
import com.ynov.dystraite.entities.PasswordResetTokens;
import com.ynov.dystraite.services.PasswordResetTokenService;
import com.ynov.dystraite.services.EmailService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@RestController
public class PasswordResetTokenController {
    @Autowired
    PasswordResetTokenService passwordResetTokenService;
    @Autowired
    UsersService userService;

    @Autowired
    UsersRepository userRepo;

    @Getter
    static private class RequestTokenBody {
        private String email;
    }
    @RequestMapping(value="/users/request-token", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Boolean requestToken(@RequestBody RequestTokenBody body) {
        String email = body.getEmail();
        System.out.println(email);
        List<Users> users = userService.getAll();
        System.out.println(users);
        Users user = userService.getById(email);
        if (userService == null) {
           throw new UserNotFoundException(email);
        }
        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createPasswordResetTokenForUser(user, token);
        EmailService.sendMail(user.getEmail(), "Password reset code", token);
        return true;
    }

    @Getter
    static private class ChangePasswordBody {
        private String email;
        private String password;
    }
    @RequestMapping(value="/users/change-password/{token}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Boolean changePassword(@PathVariable String token, @RequestBody ChangePasswordBody body) {
        String email = body.getEmail();
        String password = body.getPassword();
        Users user = userService.getById(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        PasswordResetTokens passwordResetToken = passwordResetTokenService.getByToken(token);
        if (passwordResetToken == null) {
            throw new PasswordResetTokenNotFoundException(email);
        }

        LocalDateTime now = LocalDateTime.now();
        long diff = ChronoUnit.MINUTES.between(passwordResetToken.getExpiryDate(), now);
        if (diff > 10) {
            throw new PasswordResetTokenExpiredException(email);
        }
        user.setPassword(password);
        userRepo.save(user);
        return true;
    }
}

