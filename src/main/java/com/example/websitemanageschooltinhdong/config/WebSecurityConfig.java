package com.example.websitemanageschooltinhdong.config;

import com.example.websitemanageschooltinhdong.security.filter.CustomAccessDeniedHandler;
import com.example.websitemanageschooltinhdong.security.filter.JwtRequestFilter;
import com.example.websitemanageschooltinhdong.security.filter.RestAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());//kiểm tra thử đường dẫn có jwt kèm theo ko có báo ko có quyèn
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/listNew").permitAll()
                .antMatchers(HttpMethod.GET, "/api/student/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/teacher/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/listNew/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and()
                .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }

}

