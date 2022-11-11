package fr.iocean.species.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.authorizeRequests()
	.antMatchers("/person/delete/**").hasRole("ADMIN")
	.antMatchers("/**").hasRole("USER")
	.antMatchers("/**").permitAll()
//	.anyRequest().authenticated()
	.and().httpBasic();
	
	return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("user123")).roles("USER").build();
		UserDetails userAdmin = User.withUsername("admin").password(passwordEncoder().encode("admin456")).roles("ADMIN", "USER").build();
		UserDetails johnDoe = User.withUsername("johnDoe").password(passwordEncoder().encode("password")).roles("USER").build();
		
		return new InMemoryUserDetailsManager(user, userAdmin, johnDoe);
	}
}
