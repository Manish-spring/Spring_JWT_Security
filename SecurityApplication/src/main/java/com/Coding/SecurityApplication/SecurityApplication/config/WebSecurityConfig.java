package com.Coding.SecurityApplication.SecurityApplication.config;

import com.Coding.SecurityApplication.SecurityApplication.entities.Role;
import com.Coding.SecurityApplication.SecurityApplication.filters.JwtRequestFilter;
import com.Coding.SecurityApplication.SecurityApplication.handlers.Oauth2SuccessHandler;
import com.Coding.SecurityApplication.SecurityApplication.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.Coding.SecurityApplication.SecurityApplication.entities.Role.ADMIN;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final Oauth2SuccessHandler oauth2SuccessHandler;

    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter, Oauth2SuccessHandler oauth2SuccessHandler) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.oauth2SuccessHandler = oauth2SuccessHandler;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/signup","/login","/refreshToken","/home.html").permitAll()

//                                .requestMatchers("/product").hasRole(ADMIN.name())
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login(loigConfig -> loigConfig.failureUrl("/login?error=true")
//                        .successHandler(oauth2SuccessHandler))
//                .formLogin(Customizer.withDefaults())
                .build();
    }

//    @Bean
//    UserDetailsService myUserDetails() {
//
//        UserDetails user = User.withUsername("manish")
//                .password("Test")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
