package com.ynov.dystraite.security.jwt;

import java.security.SignatureException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ynov.dystraite.security.services.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${aventure.app.jwtSecret}")
	private String jwtSecret;

	@Value("${aventure.app.jwtExpirationMsAutoLoginTrue}")
	private Long jwtExpirationMsAutoLoginTrue;

	@Value("${aventure.app.jwtExpirationMsAutoLoginFalse}")
	private Long jwtExpirationMsAutoLoginFalse;

	public String generateJwtToken(Authentication authentication) {
		// Change the duration of the token according to "Remeber Me" (30 days or 8 hours)
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Long jwtExpirationMs = jwtExpirationMsAutoLoginFalse;
		if (userPrincipal.getAutoLogin()) {
			jwtExpirationMs = jwtExpirationMsAutoLoginTrue;
		}
		// Generate JWT Token
		return Jwts.builder()
				.setSubject((userPrincipal.getEmail()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
