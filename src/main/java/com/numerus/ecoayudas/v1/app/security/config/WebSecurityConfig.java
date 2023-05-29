package com.numerus.ecoayudas.v1.app.security.config;


import com.numerus.ecoayudas.v1.app.security.constants.SecurityConstants;
import com.numerus.ecoayudas.v1.app.security.service.UserDetailsServiceImpl;
import com.numerus.ecoayudas.v1.app.security.authorization.JWTAuthorizationFilter;
import com.numerus.ecoayudas.v1.app.security.authentication.JWTAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuration class for web security.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Constructor for WebSecurityConfig.
     *
     * @param userDetailsService     UserDetailsService implementation
     * @param jwtAuthorizationFilter JWTAuthorizationFilter instance
     */

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    /**
     * Configures the security settings for HTTP requests.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);


        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeHttpRequests()
                .requestMatchers(SecurityConstants.LOGIN_URL,
                        SecurityConstants.CLIENTE_URL,
                        SecurityConstants.INSTALADOR_URL).permitAll()
                .requestMatchers(SecurityConstants.CLIENTE_URL1).hasAuthority(SecurityConstants.AUTH_CLIENTE)
                .requestMatchers(SecurityConstants.INSTALADOR_URL1).hasAuthority(SecurityConstants.AUTH_INSTALADOR)
                .requestMatchers(SecurityConstants.UPLOAD_URL, SecurityConstants.LOGOUT_URL).hasAnyAuthority(SecurityConstants.AUTH_CLIENTE, SecurityConstants.AUTH_INSTALADOR)
                .and()
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, JWTAuthenticationFilter.class)
                .build();

    }

    /**
     * Creates a CorsConfigurationSource instance with CORS configuration.
     *
     * @return CorsConfigurationSource instance
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(SecurityConstants.ANGULAR_URL);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(SecurityConstants.API_URL, configuration);
        return source;
    }

    /**
     * Configures the authentication manager.
     */

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    /**
     * Creates a PasswordEncoder instance for password encoding.
     *
     * @return PasswordEncoder instance
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}