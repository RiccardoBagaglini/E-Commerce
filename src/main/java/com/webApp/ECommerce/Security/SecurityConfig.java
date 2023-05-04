package com.webApp.ECommerce.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static String REALM = "REAME";
    private static final String[] USER_MATCHER = { "/api/articoli/**"};
    private static final String[] ADMIN_MATCHER = { "/api/articoli/tutti" };
    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

      @Bean
     public  AuthEntryPoint getBasicAuthEntryPoint(){
        return  new AuthEntryPoint();
     }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/articoli/cerca").hasRole("USER")
                .requestMatchers("/api/articoli/inserisci").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(new BCryptPasswordEncoder());
    }
}
