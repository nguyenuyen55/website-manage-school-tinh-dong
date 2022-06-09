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
                .antMatchers(HttpMethod.GET, "/api/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/listNew").permitAll()
                .antMatchers(HttpMethod.GET, "/api/khoi").permitAll()
                .antMatchers(HttpMethod.GET, "/api/chuonghoc/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/monhoc/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/cauhoi/create").permitAll()
                .antMatchers(HttpMethod.POST, "/api/listNew/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/cauhoi/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/listNew/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/listNew/delete/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/nguoiDung/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/student/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/student/list/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/teacher/search/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/listNew/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/timeTable/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/tb").permitAll()
                .antMatchers(HttpMethod.GET, "/api/tb/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/tb/create/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/baiGiang/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/baiGiang").permitAll()
                .antMatchers(HttpMethod.GET, "/api/baiGiang/search").permitAll()
                .antMatchers(HttpMethod.POST, "/api/baiGiang/create").permitAll()//role gv
                .antMatchers(HttpMethod.PUT, "/api/baiGiang/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/baiGiang/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/baiGiang/delete/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/diem").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/api/diem/hocSinh").hasRole("TEACHER")
                .antMatchers(HttpMethod.POST, "/api/questions/create").permitAll()
                .antMatchers(HttpMethod.GET, "/api/questions/chude").permitAll()
                .antMatchers(HttpMethod.POST, "/api/uploadFile").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/student/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/student/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/student/delete/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/student/detail/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/student/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/teacher/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/teacher/update").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/teacher/delete/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/teacher/detail/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/lop/create").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/lop/list").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/lop/list/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/lop/khoi/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/lop/updatelop").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/lop/updateLopHocSinh").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and()
                .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }

}

