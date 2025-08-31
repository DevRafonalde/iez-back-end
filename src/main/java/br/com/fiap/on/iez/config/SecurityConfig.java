//package br.com.fiap.on.iez.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/**").permitAll()
////                )
////                .csrf(AbstractHttpConfigurer::disable)
////                .httpBasic(AbstractHttpConfigurer::disable)
////                .formLogin(AbstractHttpConfigurer::disable);
////
////        return http.build();
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        // libera login
////                        .requestMatchers("/usuarios/login").permitAll()
////                        // libera o endpoint /ai (com ou sem query params)
////                        .requestMatchers("/ai").permitAll()
////                        // libera o restante — o PermissaoMiddleware cuida do que precisa proteger
////                        .anyRequest().permitAll()
////                )
////                // desabilita proteções que não precisamos
////                .csrf(AbstractHttpConfigurer::disable)
////                .httpBasic(AbstractHttpConfigurer::disable)
////                .formLogin(AbstractHttpConfigurer::disable)
////                .logout(AbstractHttpConfigurer::disable)
////                // desativa sessões
////                .sessionManagement(AbstractHttpConfigurer::disable);
////
////        return http.build();
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/usuarios/login").permitAll()
////                        .requestMatchers("/ai").permitAll()
////                        .anyRequest().permitAll()
////                )
////                .csrf(AbstractHttpConfigurer::disable)
////                .httpBasic(AbstractHttpConfigurer::disable)
////                .formLogin(AbstractHttpConfigurer::disable)
////                .logout(AbstractHttpConfigurer::disable)
////                .sessionManagement(AbstractHttpConfigurer::disable)
////                .anonymous(AbstractHttpConfigurer::disable); // 🔹 desativa autenticação anônima
////
////        return http.build();
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        http
////                // 🔹 autoriza tudo
////                .authorizeHttpRequests(auth -> auth
////                        .anyRequest().permitAll()
////                )
////                // 🔹 remove tudo que pode causar 401 indesejado
////                .csrf(AbstractHttpConfigurer::disable)
////                .httpBasic(AbstractHttpConfigurer::disable)
////                .formLogin(AbstractHttpConfigurer::disable)
////                .logout(AbstractHttpConfigurer::disable)
////                .sessionManagement(AbstractHttpConfigurer::disable)
////                .anonymous(AbstractHttpConfigurer::disable);
////
////        // 🔹 aqui, se quiser, adiciona seu filtro JWT ANTES do controle por @Permissao
////        // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
//                .logout(AbstractHttpConfigurer::disable)
//                .sessionManagement(AbstractHttpConfigurer::disable)
//                .anonymous(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
