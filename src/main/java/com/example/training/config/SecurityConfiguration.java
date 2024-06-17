package com.example.training.config;

import com.example.training.security.CustomUserDetailService;
import com.example.training.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private CustomUserDetailService customUserDetailService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/auth/**", "/top-rate-mentor", "/list-mentor/**", "/uploadAvatarAndView/{folderPath}", "/view/{fileName}", "/available-skill", "/certificate", "/payment/return_url/**", "/payment/cancel_url/**", "/course/list/{userName}", "/course/{id}", "/mentee/suggest-course", "/oms/**", "/app/**", "/filter-courses", "/queue/**", "/courses").permitAll()
                .requestMatchers("/user-wallet", "/mentee/support/{id}/confirm-end").hasAnyRole("MENTEE", "MENTOR")
                .requestMatchers("/educationStaff/**", "/admin/enableOrDisablePost/postId/{postId}", "/admin/viewPostDetail/{urlPostId}","/admin/postList").hasAnyRole("EDUCATION_STAFF", "ADMIN")
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .requestMatchers("/admin/supportDetail/**").hasAnyRole("MENTEE", "MENTOR", "ADMIN")
                .requestMatchers("/mentee/**", "/payment/create_payment").hasRole("MENTEE")
                .requestMatchers("/mentor/**").hasRole("MENTOR")
                .requestMatchers("/change-password", "/profile/{username}").hasAnyRole("TRANSACTION_STAFF", "EDUCATION_STAFF", "ADMIN", "MENTOR", "MENTEE")
                .requestMatchers("/transaction-staff/..").hasAnyRole("ADMIN", "TRANSACTION_STAFF")
                .anyRequest()
                .authenticated()
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }
}
