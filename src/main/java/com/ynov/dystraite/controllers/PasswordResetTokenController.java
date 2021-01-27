package com.ynov.dystraite.controllers;

import com.ynov.dystraite.exceptions.PasswordResetTokenNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.exceptions.UserNotFoundException;
import com.ynov.dystraite.services.UsersService;
import com.ynov.dystraite.entities.PasswordResetTokens;
import com.ynov.dystraite.services.PasswordResetTokenService;
import com.ynov.dystraite.services.EmailService;

import java.util.List;
import java.util.UUID;

@RestController
public class PasswordResetTokenController {
    @Autowired
    PasswordResetTokenService passwordResetTokenService;
    @Autowired
    UsersService userService;

    static public class RequestTokenBody {
        @Getter
        private String email;
    }
    @RequestMapping(value="/users/requestToken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Boolean requestToken(@RequestBody RequestTokenBody body) {
        String email = body.getEmail();
        Users user = userService.getById(email);
        if (user == null) {
           throw new UserNotFoundException(email);
        }
        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createPasswordResetTokenForUser(user, token);
        EmailService.sendMail(user.getEmail(), "Password reset code", token);
        return true;
    }

    @RequestMapping(value="/users/changePassword/{token}", method = RequestMethod.POST, produces =  MediaType.APPLICATION_JSON_VALUE)
    public Boolean changePassword(@PathVariable String token, String email, String password) {
        Users user = userService.getById(email);
        if (user == null) {
            throw new UserNotFoundException(email);
        }
        PasswordResetTokens passwordResetToken = passwordResetTokenService.getByToken(token);
        if (passwordResetToken == null) {
            throw new PasswordResetTokenNotFoundException(email);
        }

        user.setPassword(password);
        return true;
    }
}

