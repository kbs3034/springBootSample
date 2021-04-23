package com.bskim.common.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//static resource ignoring
    @Override
    public void configure(WebSecurity web) {
    	// static/**
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        // resources/**
        web.ignoring().antMatchers("/resources/**");
        //login
        web.ignoring().antMatchers("/USRLGI00101R");
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/")
                .successForwardUrl("/views/sessionCheckPage.html")
                .failureForwardUrl("/")
                .permitAll()
                .and();
//                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
}
