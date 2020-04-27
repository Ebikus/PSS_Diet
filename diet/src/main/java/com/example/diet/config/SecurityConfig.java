package com.example.diet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers("/rest/delegation/addDelegation").hasAnyRole("ADMIN","USER")
                .antMatchers("/rest/delegation/removeDelegation").hasAnyRole("ADMIN", "USER")
                .antMatchers("/rest/delegation/changeDelegation").hasAnyRole("ADMIN", "USER")
                .antMatchers("/rest/delegation/getAllByUser").hasAnyRole("ADMIN", "USER")
                .antMatchers("/rest/delegation/getAllDelegtions").hasRole("ADMIN")
                .antMatchers("/rest/delegation/getAllByDateStartDesc").hasRole("ADMIN")
                .antMatchers("/rest/user/changePassword").hasAnyRole("ADMIN", "USER")
                .antMatchers("/rest/user/register").hasRole("ADMIN")
                .antMatchers("/rest/user/getAllUsers").hasRole("ADMIN")
                .antMatchers("/rest/user/getAllUsersByRole").hasRole("ADMIN")
                .antMatchers("/rest/user/deleteUserById").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html/**").hasRole("ADMIN")
                .and().formLogin().loginPage(LOGIN_URL).loginProcessingUrl(LOGIN_PROCESSING_URL)
                .and().oauth2Login().loginPage(LOGIN_URL)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    @Bean
    public  BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
