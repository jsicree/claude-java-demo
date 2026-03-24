/**
 * Spring MVC configuration enabling CORS for the REST API.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-22
 */
package com.example.claudejavademo.adapter.in.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebConfig implements WebMvcConfigurer {

    /**
     * Allows all origins on {@code /api/**} to support cross-origin requests from the standalone
     * React frontend (claude-react-demo) in both development and production environments.
     *
     * @param registry the CORS registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE");
    }
}
