package com.ftn.WebXML2018.XWS_2018_Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class CORSConfig extends WebMvcConfigurerAdapter {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
        	 @Override
             public void addCorsMappings(CorsRegistry registry) {
                 registry.addMapping("/**")
                 .allowedOrigins("*")
                 .allowedMethods("*")
                 .allowedHeaders("*")
                 .exposedHeaders("Content-Type", "Date", "Total-Count", "Authorization")
                 .maxAge(3600);
             }
		};
	}
}
