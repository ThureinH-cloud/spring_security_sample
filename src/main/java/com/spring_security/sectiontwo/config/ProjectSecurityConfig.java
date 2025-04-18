package com.spring_security.sectiontwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.requiresChannel(rcc->rcc.anyRequest().requiresInsecure())
                .csrf(csrfconfig->csrfconfig.disable()).authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myLoans","/myBalance","/myCards").authenticated()
                .requestMatchers("/contact","/notices","/error","/register").permitAll());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
////       UserDetails user= User.withUsername("user").password("{noop}User@12345").authorities("read").build();
////       UserDetails admin= User.withUsername("admin").password("{bcrypt}$2a$12$4QJL6MuE2ZvxIJ7Tib/fU.NoxinECSklD8wBK5h0U4gZxGMWOXelS").authorities("admin").build();
////       return new InMemoryUserDetailsManager(user, admin);
//        return  new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public CompromisedPasswordChecker compromisedPasswordChecker() {
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }
}
