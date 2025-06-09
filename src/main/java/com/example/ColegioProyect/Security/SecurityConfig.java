package com.example.ColegioProyect.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig {
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(
                                "/nivel/consultarNiveles",
                                "/nivel/cambiarStatus",
                                "/nivel/modificarNivel",
                                "/nivel/crearNivel",

                                "/periodo/consultarPeriodo",
                                "/periodo/modificarPeriodo",
                                "/periodo/registrarPeriodo",

                                "/gradoGrupo/consultarGradoGrupos",
                                "/gradoGrupo/modificarGradoGrupos",
                                "/gradoGrupo/registrarGradoGrupos",
                                "/gradoGrupo/cambiarStatusGradosGrupos",

                                "/usuario/consultarUsuario",
                                "/usuario/estudianteConPadre",
                                "/usuario/padreConEstudiante",
                                "/usuario/soloEstudiantes",
                                "/usuario/soloPadres",
                                "/usuario/soloProfesores",
                                "/usuario/crearUsuario",
                                "/usuario/modificarUsuario",
                                "/usuario/cambiarStatusUsuario",

                                "/materia/obtenerMaterias",
                                "/materia/crearMateria",
                                "/materia/modificarMateria",

                                "/evento/obtenerEventos",
                                "/evento/crearEvento",
                                "/evento/modificarEvento"

                        ).hasAuthority("ADMINISTRADOR")
                        .anyRequest().authenticated()

                ).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList( "GET", "POST", "PUT", "DELETE", "OPTIONS" ));
        configuration.setAllowedHeaders(Arrays.asList( "Content-Type", "Authorization" ));
        configuration.setExposedHeaders(Arrays.asList( "Authorization" ));
        configuration.setAllowCredentials(true);

        configuration.addAllowedHeader("Origin");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
