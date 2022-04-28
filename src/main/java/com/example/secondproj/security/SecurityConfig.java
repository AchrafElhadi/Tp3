package com.example.secondproj.security;

import com.example.secondproj.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // PasswordEncoder passwordEncoder=passwordEncoder();
//        PasswordEncoder p=new BCryptPasswordEncoder();
//        String pass=p.encode("1234");
//
//        System.out.println(pass);
//        auth.inMemoryAuthentication().withUser(  "user1").password(pass).roles("USER");
//        auth.inMemoryAuthentication().withUser(  "user2").password (pass).roles("USER");
//        auth.inMemoryAuthentication().withUser( "admin").password (pass).roles("USER","ADMIN");

//        auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery("select username as principal,password as credentials,active from user where username=?")
//                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
//                .rolePrefix("ROLE_").passwordEncoder(new BCryptPasswordEncoder());

        auth.userDetailsService(userDetailsService);
//
//
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/index/**").hasAuthority("USER");

        http.authorizeRequests().antMatchers("/delete/**","/edit/**","/save/**","/formPatients/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");

    }

}
