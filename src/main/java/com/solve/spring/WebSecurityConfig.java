package com.solve.spring;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * user 아이디, 패스워드 인메모리 설정
	 * 
	 * @return
	 */
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) 
	      throws Exception {
	        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        auth.inMemoryAuthentication()
	          .withUser("user")
	          .password(encoder.encode("user"))
	          .roles("USER");
	    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.headers().frameOptions().disable()
                .and()
        		.authorizeRequests()
                    .antMatchers("/console/**","/resources/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .csrf()
                    	.ignoringAntMatchers("/console/**")
                    	.and()
            			.headers()
        				.frameOptions().sameOrigin()
        				.httpStrictTransportSecurity().disable()
                 .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
}