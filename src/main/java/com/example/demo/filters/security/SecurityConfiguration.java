package com.example.demo.filters.security;

import com.example.demo.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;




    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests((request) -> request
                        .antMatchers("/auth/**").permitAll()
                        .antMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .antMatchers(HttpMethod.GET,"/rooms/**").hasAnyAuthority("CLIENT", "ADMIN", "MANAGER")
                        .antMatchers(HttpMethod.POST,"/rooms/**").hasAuthority( "ADMIN")
                        .antMatchers(HttpMethod.PUT,"/rooms/**").hasAuthority("ADMIN")
                        .antMatchers(HttpMethod.DELETE,"/rooms/**").hasAuthority("ADMIN")
                        .antMatchers("/orders/").hasAnyAuthority("ADMIN","MANAGER")
                        .antMatchers("/orders/accepted").hasAnyAuthority("ADMIN","MANAGER")
                        .antMatchers("/orders/rejected").hasAnyAuthority("ADMIN","MANAGER")
                        .antMatchers("/orders/pending").hasAnyAuthority("ADMIN","MANAGER")
                        .antMatchers("/orders/my").hasAuthority("CLIENT")
                        .antMatchers(HttpMethod.POST,"/orders/**").hasAuthority("CLIENT")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
