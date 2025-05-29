package com.Dev.Bug_Tracking_System2.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Dev.Bug_Tracking_System2.Service.UserService;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	
	@Autowired
	UserService userService; 
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Bean
	AuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return provider;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(request->request
					.requestMatchers("/login","/register").permitAll()
					//.requestMatchers("/api/admin/**").hasAuthority("ADMIN")
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.logout(logout->logout.logoutSuccessUrl("/login?logout"))
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
		
	}
	

	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}

}
