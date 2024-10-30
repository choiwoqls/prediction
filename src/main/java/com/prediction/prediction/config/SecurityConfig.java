package com.prediction.prediction.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prediction.util.ApiResponse;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.prediction.prediction.jwt.JwtUtil;
import com.prediction.prediction.jwt.LoginFilter;
import com.prediction.prediction.repository.jwt.RefreshRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

        // AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
        private final AuthenticationConfiguration authenticationConfiguration;

        private final JwtUtil jwtUtil;

        private final RefreshRepository refreshRepository;

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

                return configuration.getAuthenticationManager();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {

                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf((auth) -> auth.disable());

                http
                                .formLogin((auth) -> auth.disable());

                http
                                .httpBasic((auth) -> auth.disable());

                http
                                .authorizeHttpRequests((auth) -> auth
                                                .requestMatchers("/user/login", "/user/sign-up", "user/{id}", "hello")
                                                .permitAll()
                                                .requestMatchers("/admin").hasRole("ADMIN")
                                                .requestMatchers("/reissue").permitAll()
                                                .anyRequest().authenticated());

                // http
                // .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

                http
                                .addFilterAt(
                                                new LoginFilter(authenticationManager(authenticationConfiguration),
                                                                jwtUtil, refreshRepository),
                                                UsernamePasswordAuthenticationFilter.class);

                // http
                // .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository),
                // LogoutFilter.class);

                // 세션 설정
                http
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                return http.build();


        }


//            private final JwtAuthenticationEntryPoint unauthorizedHandler;
//
//            private final CustomUserDetailsService userService;
//
//            private static final String[] AUTH_WHITELIST = {
//                    "/swagger-resources/**",
//                    "/configuration/ui",
//                    "/configuration/security",
//                    "/swagger-ui/**",
//                    "/webjars/**",
//                    "/swagger-ui.html",
//                    "/v3/api-docs/**",
//                    "/actuator/*",
//                    "/api/auth/**",
//                    "/api/users/create-admin",
//                    "/api/users/create-invite-user",
//                    "/api/users/is-code-valid",
//                    "/api/documents/download/**",
//            };
//
                //Spring Security에서 인증 오류가 발생할 때 사용자에게 JSON 형식의 에러 응답을 반환
//            @Bean
//            public AuthenticationEntryPoint authenticationErrorHandler() {
//                return (request, response, ex) -> {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                    ServletOutputStream out = response.getOutputStream();
//                    new ObjectMapper().writeValue(out, new ApiResponse<String>("Invalid or missing auth token." +
//                            "",  (Object) "", HttpStatus.UNAUTHORIZED));
//                    out.flush();
//                };
//            }
//
//              Spring Security에서 접근 권한이 없는 요청이 들어왔을 때 실행
//            @Bean
//            public AccessDeniedHandler accessDeniedHandler() {
//                return (request, response, ex) -> {
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                    ServletOutputStream out = response.getOutputStream();
//                    new ObjectMapper().writeValue(out, new ApiResponse<String>("You are not allowed to access this resource.", (Object) "", HttpStatus.FORBIDDEN));
//                    out.flush();
//                };
//            }
//
//            @Bean
//            public CorsConfigurationSource corsConfigurationSource() {
//                CorsConfiguration configuration = new CorsConfiguration();
//                configuration.addAllowedOrigin("*");
//                configuration.addAllowedMethod("*");
//                configuration.addAllowedHeader("*");
//
//                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                source.registerCorsConfiguration("/**", configuration);
//                return source;
//            }
//
//
//            @Bean
//            public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//                http
//                        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                        .csrf(AbstractHttpConfigurer::disable)
//                        .authorizeHttpRequests(request -> request
//                                .requestMatchers(AUTH_WHITELIST).permitAll()
//                                .anyRequest().authenticated())
//                        .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
//                        .authenticationProvider(authenticationProvider())
//                        .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                return http.build();
//            }
//
//            @Bean
//            public JwtAuthenticationFilter jwtAuthenticationFilter() {
//                return new JwtAuthenticationFilter();
//            }
//
//            @Bean
//            public PasswordEncoder passwordEncoder() {
//                return new BCryptPasswordEncoder();
//            }
//
//            @Bean
//            public AuthenticationProvider authenticationProvider() {
//                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//                authProvider.setUserDetailsService(userService);
//                authProvider.setPasswordEncoder(passwordEncoder());
//                return authProvider;
//            }
//
//            @Bean
//            public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
//                    throws Exception {
//                return config.getAuthenticationManager();
//            }
}
