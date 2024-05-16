package com.centroxy.wso2centroxythymeleaf.config;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class
SecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    public AuthenticationSuccessHandler customSuccessHandler;

    @Bean
    public UserDetailsService getuserDetailsServices()
    {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder getpasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider()

    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getuserDetailsServices());
        daoAuthenticationProvider.setPasswordEncoder(getpasswordEncoder());

        return daoAuthenticationProvider;
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
//		.antMatchers("/teacher/**").access("hasRole('ROLE_TEACHER')")
                .antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
                .successHandler(customSuccessHandler).and().csrf().disable();
    }



}

