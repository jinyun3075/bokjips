package com.bokjips.server.util.security;

import com.bokjips.server.util.security.filter.ApiCheckFilter;
import com.bokjips.server.util.security.filter.ApiLoginFilter;
import com.bokjips.server.util.security.handler.ApiLoginFailHandler;
import com.bokjips.server.util.security.handler.ApiLoginSuccessHandler;
import com.bokjips.server.util.security.service.BokjipsUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BokjipsUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable();

        http.addFilterBefore(apiCheckFilter(),UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/corp");
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/user/login");
        apiLoginFilter.setAuthenticationManager(authenticationManager());
        apiLoginFilter.setAuthenticationSuccessHandler(new ApiLoginSuccessHandler());
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }
}
