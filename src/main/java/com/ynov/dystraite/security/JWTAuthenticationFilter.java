package com.ynov.dystraite.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.models.CustomUserDetails;
import com.ynov.dystraite.models.UserAuth;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Users u = new ObjectMapper().readValue(req.getInputStream(), Users.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            u.getEmail(),
                            u.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        String token = generateAndSetToken(customUserDetails.getUsername());

        UserAuth userAuthResponse = new UserAuth(customUserDetails.getUser(), token);

        //convertit l'objet utilisateur en JSON
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String userAuthResponseJson = ow.writeValueAsString(userAuthResponse);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(userAuthResponseJson);
    }

    public static String generateAndSetToken(String email){
        return SecurityConstants.TOKEN_PREFIX + JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(HMAC512(SecurityConstants.SECRET.getBytes()));


    }
}
