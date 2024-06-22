package com.example.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> auth
			.requestMatchers("/", "/member/signup", "/member/signupProc","/board/list","/board/read").permitAll()
			.requestMatchers("/board/write","/board/modify","/board/del").hasRole("USER") 
			.requestMatchers("/board/reply").authenticated() 
	        .anyRequest().permitAll()
		);
	        
        http.formLogin(auth -> auth
                .loginPage("/member/login").permitAll()
                .loginProcessingUrl("/member/loginProc")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/member/login?error=true")
                .permitAll()
        );

        http.logout(auth -> auth
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        );

        http.csrf(csrf -> csrf.disable());
        
		return http.build();
	}

}
