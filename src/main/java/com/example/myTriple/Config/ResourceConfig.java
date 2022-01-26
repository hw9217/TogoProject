package com.example.myTriple.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( // TODO 얘의역할은 무엇일까? 'addResourceHandler'
                "/images/**","/css/**","/js/**", "/assets/**")
                .addResourceLocations("classpath:/static/images/"
                ,"classpath:/static/css/","classpath:/static/js/", "classpath:/static/assets/");

    }
}
