package com.example.demo;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                    .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .addFilterBefore((request, response, chain) -> {
                    val header = ((HttpServletRequest)request).getHeader("MOCK_AUTHORITY");
                    if (header != null){
                        val authorities = Stream.of(header.split(","))
                                .filter(not(String::isEmpty))
                                .toArray(String[]::new);
                        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
                        SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken("user", "pw", authorities));
                    }
                    chain.doFilter(request, response);
                }, BasicAuthenticationFilter.class);
        //@formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new TestingAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
