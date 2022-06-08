package com.bokjips.server.util.security;

import com.bokjips.server.util.security.filter.ApiCheckFilter;
import com.bokjips.server.util.security.filter.ApiLoginFilter;
import com.bokjips.server.util.security.handler.ApiLoginFailHandler;
import com.bokjips.server.util.security.handler.ApiLoginSuccessHandler;
import com.bokjips.server.util.security.service.BokjipsUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Log4j2
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable();

        http.addFilterBefore(apiCheckFilter(),UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/corp/insert");
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/user/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        apiLoginFilter.setAuthenticationSuccessHandler(new ApiLoginSuccessHandler());
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
