package com.metro.Metro.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	
	public static final String JWT_KEY = JWTConstants.JWT_KEY;

	// for extracting the email from Jwt token
		public String extractEmailfromToken(String jwtToken) {
			SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
			var tokenAfterRemovingBearer = jwtToken.substring(7);
			Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(tokenAfterRemovingBearer).getPayload();
			String username = String.valueOf(claims.get("email"));
			return username;
		}
		
}
