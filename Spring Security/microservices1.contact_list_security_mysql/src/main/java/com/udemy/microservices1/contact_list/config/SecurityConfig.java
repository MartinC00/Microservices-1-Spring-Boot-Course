package com.udemy.microservices1.contact_list.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    /*@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(List.of(
                                                User.withUsername("John")
                                                    .password("$2a$12$D..sIspumiQtyRX27vpY2O96ES1P0LZDlDjhiNG25qnB3Da99byTC") //similar to 'John' but encoded by BCryptPasswordEncoder
//                                                    .password("{noop}John")
                                                    .roles("USERS")
                                                    .build(),
                                                User.withUsername("Barney")
                                                    .password("{noop}Barney")
                                                    .roles("OPERATOR")
                                                    .build(),
                                                User.withUsername("Lisa")
                                                    .password("$2a$12$vVRmQl7N7Vu5NKok21Gc6.v7Kcm5dUHE98o6HuBN6XUEw9E9Fjt2q")
//                                                    .password("{noop}Lisa")
                                                    .roles("USERS","ADMIN")
                                                    .build()));
    }
    @Bean
    public BCryptPasswordEncoder BCRyptEncoder (){
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setUrl("jdbc:mysql://localhost:3306/contact_list");
        driver.setUsername("root");
        driver.setPassword("root");
        JdbcUserDetailsManager jdbcDetails = new JdbcUserDetailsManager(driver);
        jdbcDetails.setUsersByUsernameQuery("SELECT user, password, enabled from users where user=?");
        jdbcDetails.setAuthoritiesByUsernameQuery("SELECT u.user, r.role FROM rolexuser r INNER JOIN users u ON u.IdUser = r.IdUser where u.user=?");
        return jdbcDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(cus -> cus.disable())
                .authorizeHttpRequests(aut ->
                    aut.requestMatchers(HttpMethod.POST, "contactList").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE, "contactList/**").hasAnyRole("OPERATOR", "ADMIN")
                       .requestMatchers("contactList").authenticated()
                       .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
