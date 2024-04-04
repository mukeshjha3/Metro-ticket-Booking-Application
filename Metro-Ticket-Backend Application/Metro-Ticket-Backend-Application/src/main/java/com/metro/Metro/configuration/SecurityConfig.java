package com.metro.Metro.configuration;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.metro.Metro.Filter.JWTTokenGenerator;
import com.metro.Metro.Filter.JwtTokenValidator;

@Configuration
//@EnableWebSecurity(debug = true)
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		 http.csrf(csrf -> csrf.disable());
         http.authorizeHttpRequests(authorizeRequests -> 
             authorizeRequests
                 .requestMatchers(HttpMethod.POST, "/api/user/register_user").permitAll()
                 .requestMatchers(HttpMethod.POST, "/api/user/register_admin").permitAll()
                 .requestMatchers(HttpMethod.POST, "/login").permitAll()
                 .anyRequest().authenticated()
                 
         )
				  .addFilterAfter(new JWTTokenGenerator(), BasicAuthenticationFilter.class)
				  .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class);
         
		 http.sessionManagement((session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.httpBasic(withDefaults());
		return http.build(); 
		
	}
}
