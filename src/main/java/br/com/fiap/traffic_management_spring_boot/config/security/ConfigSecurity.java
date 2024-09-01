package br.com.fiap.traffic_management_spring_boot.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Autowired
    private VerifyToken verifyToken;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorization ->
                                authorization
                                        .requestMatchers(HttpMethod.POST, "/auth/sign-in")
                                        .permitAll()

                                        .requestMatchers(HttpMethod.POST, "/auth/sign-up")
                                        .permitAll()

                                        .requestMatchers(HttpMethod.GET, "/api/traffic-light")
                                        .hasAnyRole("ADMIN", "ACCOUNT")

                                        .requestMatchers(HttpMethod.POST, "/api/traffic-light")
                                        .hasRole("ADMIN")

                                        .requestMatchers(HttpMethod.DELETE, "/api/traffic-light")
                                        .hasRole("ADMIN")

                                        .requestMatchers(HttpMethod.PUT, "/api/traffic-light")
                                        .hasRole("ADMIN")

                                        .anyRequest()
                                        .authenticated()
                )
                .addFilterBefore(verifyToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws  Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
