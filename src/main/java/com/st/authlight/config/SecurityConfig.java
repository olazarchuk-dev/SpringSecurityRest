package com.st.authlight.config;

import com.st.authlight.config.jwt.JwtCsrfFilter;
import com.st.authlight.config.jwt.JwtTokenRepository;
import com.st.authlight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final JwtTokenRepository jwtTokenRepository;
    private final HandlerExceptionResolver handlerExceptionResolver;

//    @Bean
//    public PasswordEncoder devPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                    .addFilterAt(new JwtCsrfFilter(jwtTokenRepository, handlerExceptionResolver), CsrfFilter.class)
                    .csrf().ignoringAntMatchers("/**")
                .and()
                    .authorizeRequests()
                    .antMatchers(new String[]{
                            "/webjars/**",
                            "/swagger-resources/**",
                            "/swagger-ui.html",
                            "/swagger-ui/",
                            "/swagger-ui/**",
                            "/v3/api-docs",
                            "/v3/api-docs/**",
                            "/management/**",
                            "/api/**",
                    })
                    .authenticated()
                .and()
                    .httpBasic()
                    .authenticationEntryPoint(((request, response, e) -> handlerExceptionResolver.resolveException(request, response, null, e)));
    }
}
