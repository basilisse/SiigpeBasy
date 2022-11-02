/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.dpe.siigpe.ca.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import org.springframework.session.Session;
import org.springframework.session.jdbc.JdbcIndexedSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author Diary Manjaka <dikadeza2@gmail.com>
 * @param <S>
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfiguration<S extends Session> {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    JdbcIndexedSessionRepository sessionRepository;

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setMaxAge(Long.valueOf(3600));
        configuration.setAllowCredentials(Boolean.TRUE);
        configuration.setAllowedHeaders(Arrays.asList("x-auth-token", "x-xsrf-token", "content-type", "origin", "accept", "Authorities"));
        configuration.setExposedHeaders(Arrays.asList("Authorities"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "POST", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RequestCache refererRequestCache() {
        return new HttpSessionRequestCache() {
            @Override
            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                String referrer = request.getHeader("referer");
                if (referrer != null) {
                    request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HttpServletRequest request) throws Exception {
        http
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeHttpRequests((auth)
                        -> auth
                        .antMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                /*.csrf((csrf)
                        -> csrf
                        .csrfTokenRepository(csrfTokenRepository(request))
                )*/
                .csrf().disable()
                .sessionManagement((session)
                        -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));
        return http.build();
    }

    /*@Bean
    public FilterRegistrationBean<XAuthTokenFilter> loggingFilter() {
        FilterRegistrationBean<XAuthTokenFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new XAuthTokenFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/api/auth/isAuthenticated", "/api/user/*"));
        return registrationBean;
    }*/

    @Bean
    public SpringSessionBackedSessionRegistry<S> sessionRegistry() {
        return new SpringSessionBackedSessionRegistry(this.sessionRepository);
    }

    /*private class AuthenticationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Login success");
            data.put("isAuthenticated", true);
            data.put("authToken", request.getSession().getId());

            response.setStatus(HttpServletResponse.SC_OK);
            response
                    .getOutputStream()
                    .println(objectMapper.writeValueAsString(data));
        }
    }*/

    /*private class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void onAuthenticationFailure(
                HttpServletRequest request,
                HttpServletResponse response,
                AuthenticationException exception)
                throws IOException, ServletException {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.out.println("LOGIN ERROR");
            Map<String, Object> data = new HashMap<>();
            data.put("message", "Login failed");
            data.put(
                    "exception",
                    exception.getMessage());
            response.getOutputStream()
                    .println(objectMapper.writeValueAsString(data));
        }
    }*/

    /*private CsrfTokenRepository csrfTokenRepository(HttpServletRequest request) {
        CookieCsrfTokenRepository repository = new CookieCsrfTokenRepository();
        repository.setCookiePath("/");
        repository.generateToken(request);
        repository.setCookieDomain("localhost");
        repository.setCookieHttpOnly(false);
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }*/

}

