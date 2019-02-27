package com.SmoothStack.EurekaBorrower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages="com.SmoothStack.EurekaBorrower")
public class EurekaBorrowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaBorrowerApplication.class, args);
	}
	
	@EnableWebSecurity
	static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
		}
	}

}