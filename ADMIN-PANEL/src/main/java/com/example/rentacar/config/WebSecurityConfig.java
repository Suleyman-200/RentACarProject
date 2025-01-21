package com.example.rentacar.config;


import com.example.rentacar.security.jwt.AuthEntryPointJwt;
import com.example.rentacar.security.jwt.AuthTokenFilter;
import com.example.rentacar.security.services.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
    }
    private static final String ADMIN="ADMIN";


    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/list/**").permitAll()
                .antMatchers("/api/feedback").permitAll()
                .antMatchers("/api/forget/**").permitAll()
                .antMatchers("/api/about-us/read").permitAll()
                .antMatchers("/api/contact/all").permitAll()
                .antMatchers("/api/contact-us/numbers").permitAll()
                .antMatchers("/api/brands/**").hasRole(ADMIN)
                .antMatchers("/api/models/**").hasRole(ADMIN)
                .antMatchers("/api/cars/**").hasRole(ADMIN)
                .antMatchers("/api/users/**").hasRole(ADMIN)
                .antMatchers("/api/new/about-us").hasRole(ADMIN)
                .antMatchers("/api/about-us").hasRole(ADMIN)
                .antMatchers("/api/api/contact/uptade").hasRole(ADMIN)
                .antMatchers("/api/api/contact/delete/*").hasRole(ADMIN)
                .antMatchers("/api/api/contact/add").hasRole(ADMIN)
                .antMatchers("/api/feedbacks").hasRole(ADMIN)
                .antMatchers("/api/rents/all").hasRole(ADMIN)
                .antMatchers("/api/rents/delete/**").hasRole(ADMIN)
                        .anyRequest().authenticated();


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }




}