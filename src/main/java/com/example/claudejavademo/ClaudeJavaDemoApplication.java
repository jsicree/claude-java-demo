/**
 * Entry point for the claude-java-demo Spring Boot application.
 *
 * @author Joe Sicree (test@test.com)
 * @since 2026-03-11
 */
package com.example.claudejavademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClaudeJavaDemoApplication {

	/**
	 * Application entry point.
	 *
	 * @param args command-line arguments passed to Spring Boot
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClaudeJavaDemoApplication.class, args);
	}

}
