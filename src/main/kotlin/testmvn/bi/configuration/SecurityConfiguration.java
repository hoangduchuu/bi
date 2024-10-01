package testmvn.bi.configuration;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private static final String[] WHITE_LIST_URL = {
      "/api/v1/auth/**",
      "/api/v1/home",
      "/api/v1/home/promotion",
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui/**",
      "/webjars/**",
      "/swagger-ui.html"};

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req ->
                req
                    .requestMatchers(WHITE_LIST_URL).permitAll()
                    .requestMatchers(HttpMethod.GET, "**").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/api/v1/banners/**").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
//                    .requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll()
                    .anyRequest()
                    .authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .cors(Customizer.withDefaults())
    ;

    return http.build();
  }
}
