package com.ynov.dystraite.models;

import com.ynov.dystraite.entities.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUserDetails extends User {

    private Users user;

    public CustomUserDetails(Users user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}
