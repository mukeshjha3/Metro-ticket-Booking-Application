package com.metro.Metro.Filter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.metro.Metro.utils.JWTConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JWTTokenGenerator extends OncePerRequestFilter {

	public static final String JWT_KEY = JWTConstants.JWT_KEY;
	public static final String JWT_HEADER = JWTConstants.JWT_HEADER;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (null != authentication) {
			
			SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8)); 
                 
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			
			String role = null;
			for (GrantedAuthority element : authorities) {
				 role =element.getAuthority();
			}
			
			
			String jwt = Jwts.builder()
					.issuer("Kolkata-Metro-Corporation")
					.subject("Token")
					.claim("email", authentication.getName())
					.claim("Role", role)
					.issuedAt(new Date())
					.expiration(new Date((new Date()).getTime() + 15552000))
					.signWith(key)
					.compact();
			
			response.setHeader(JWT_HEADER, jwt );
			System.out.println("Jwt token : " + jwt);
		}

		filterChain.doFilter(request, response);
	}


	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !request.getServletPath().equals("/login");
	}

}
