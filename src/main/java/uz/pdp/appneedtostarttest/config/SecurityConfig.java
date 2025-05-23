package uz.pdp.appneedtostarttest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.appneedtostarttest.entity.User;
import uz.pdp.appneedtostarttest.security.JwtAuthenticationEntryPoint;
import uz.pdp.appneedtostarttest.security.JwtAuthenticationFilter;
import uz.pdp.appneedtostarttest.utils.AppConstant;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableJpaAuditing
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(AppConstant.OPEN_PAGES)
                                .permitAll()
                                .requestMatchers(HttpMethod.OPTIONS)
                                .permitAll()
                                .requestMatchers("/",
                                        "/favicon.ico",
                                        "//*.png",
                                        "//*.gif",
                                        "//*.svg",
                                        "//*.jpg",
                                        "//*.html",
                                        "//*.css",
                                        "//*.js",
                                        "/swagger-ui.html",
                                        "/swagger-resources/",
                                        "/v2/",
                                        "/csrf",
                                        "/webjars/",
                                        "/v2/api-docs",
                                        "/configuration/ui")
                                .permitAll()
                                .requestMatchers("/api/**").authenticated()
                                .anyRequest().permitAll()
                )
                .exceptionHandling(handling -> {
                    handling.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                    handling.accessDeniedHandler(myAccessDeniedHandler);
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuditorAware<UUID> auditingConfiguration() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof User user) {
                return Optional.of(user.getId());
            }
            return Optional.empty();
        };
    }
}
