package com.metro.Metro.Filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.metro.Metro.utils.JWTConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

	public static final String JWT_KEY = JWTConstants.JWT_KEY;
	public static final String JWT_HEADER = JWTConstants.JWT_HEADER;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException,
			IOException {
		
               String Jwt_Token = request.getHeader(JWT_HEADER);

	           if (Jwt_Token != null) {
			
			try {
				SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
				var tokenAfterRemovingBearer = Jwt_Token.substring(7);
				Claims claims = Jwts.parser()
						.verifyWith(key)
						.build()
						.parseSignedClaims(tokenAfterRemovingBearer)
						.getPayload();
				
				String username = String.valueOf(claims.get("username"));
				String role = String.valueOf(claims.get("Role"));
				
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(role));
				
				Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);

			}
			catch (ExpiredJwtException e) {
				
			}
			
			catch (Exception e) {
				e.printStackTrace();
				throw new BadCredentialsException("Invalid Token received!");
				
			}
				
	}
		
		filterChain.doFilter(request, response);

}
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().equals("/login");

			
		}

		
}
