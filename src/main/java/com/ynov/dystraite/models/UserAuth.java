package com.ynov.dystraite.models;

import com.ynov.dystraite.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserAuth {

    private Users user;

    private String token;
}
