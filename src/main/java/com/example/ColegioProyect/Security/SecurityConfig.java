package com.example.ColegioProyect.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        http
                // ... tus otras configuraciones
                .addFilterAfter((request, response, chain) -> {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    if (auth != null) {
                        System.out.println("Authorities en la solicitud: " + auth.getAuthorities());
                    }
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class);

        http
                .csrf(csrf -> csrf.disable()) // Forma moderna de deshabilitar CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login" ).permitAll()
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
                                //"/usuario/soloEstudiantes",
                                //"/usuario/soloPadres",
                                //"/usuario/soloProfesores",
                                "/usuario/crearUsuario",
                                "/usuario/modificarUsuario",
                                "/usuario/cambiarStatusUsuario",

                                "/materia/obtenerMaterias",
                                "/materia/crearMateria",
                                "/materia/modificarMateria",

                                "/evento/obtenerEventos",
                                "/evento/crearEvento",
                                "/evento/modificarEvento",

                                "/cloudinary/imagen/subir",
                                "/cloudinary/imagen/eliminar/{publicId}",

                                "/registroAsistencia/listadoAsistencia",
                                "/registroAsistencia/asistenciaActual"
                        ).hasAuthority("ADMINISTRADOR")

                        .requestMatchers(
                                "/usuario/soloPadres",
                                "/usuario/buscarPadre/{idUsuario}",
                                "/usuario/buscarEstudiante/{idUsuario}",

                                "/notificacionToken/limpiarTokensInvalidos",
                                "/notificacionToken/registrarDispositivoToken",
                                "/notificacionToken/enviarNotificacionAsistencia",
                                "/registroAsistencia/crearAsistencia"
                        ).hasAnyAuthority("PADRE", "SUBADMIN", "ADMINISTRADOR")

                        .requestMatchers(
                                "/usuario/soloProfesores",
                                "/usuario/buscarProfesor/{idUsuario}"
                        )
                        .hasAnyAuthority("PROFESOR", "ADMINISTRADOR")

                        .requestMatchers(
                                "/usuario/soloEstudiantes"
                        ).hasAnyAuthority("ESTUDIANTE", "ADMINISTRADOR")

                        .anyRequest().authenticated()

                ).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://192.168.1.93:8080", "http://localhost:8081", "http://192.168.1.93:8081"));
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
