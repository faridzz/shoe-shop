package com.fz.onlineshoestore.configs.securityConfig;


import com.fz.onlineshoestore.model.UserObj;
import com.fz.onlineshoestore.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return username -> {
            UserObj user = userRepository.findByUsername(username);
            if (user.getUsername().contains("admin") || user.getUsername() == "admin") {
                UserDetails userBuilder = User.builder().username(user.getUsername())
                        .password(user.getPassword())
                        .roles("ADMIN").build();
                return userBuilder;
            } else if (user != null) {
                UserDetails userBuilder = User.builder().username(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER").build();

                return userBuilder;
            }
            throw new UsernameNotFoundException("user " + username + " not found");
        };

    }

//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
//        return manager;
//
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
                )
//                .formLogin(form -> form.loginProcessingUrl("/api/login")).logout(logout -> logout.logoutUrl("/api/logout"))
                .httpBasic(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}