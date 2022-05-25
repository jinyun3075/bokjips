package com.bokjips.server.util.module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModuleConfig {
    @Bean
    public PageModule pageModule() {return new PageModule();}
}
