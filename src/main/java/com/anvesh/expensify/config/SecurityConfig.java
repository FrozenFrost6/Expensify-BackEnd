package com.anvesh.expensify.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.anvesh.expensify.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetails;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
    	http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {});
    	http.httpBasic(httpBasic -> {});
    	http.userDetailsService(customUserDetails);
    	http.authorizeHttpRequests(auth -> auth
    			.requestMatchers("/users", "/expenses").authenticated()
    			.anyRequest().permitAll()
    			
    			);




		return http.build();
	}
    
    
 // Define a bean to configure CORS globally
    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply CORS to all endpoints
                        .allowedOrigins("http://localhost:3000") // Add your frontend origin here
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow credentials like cookies or HTTP authentication
            }
        };
    }
	
}
