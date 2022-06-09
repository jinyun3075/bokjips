package com.bokjips.server.util.module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ModuleConfig implements WebMvcConfigurer {
    @Bean
    public PageModule pageModule() {return new PageModule();}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://www.nepool.co.kr","http://front:3000","http://localhost:3000")
                .allowedMethods("*") // 기타 설정
                .allowedHeaders("*");
    }
}
