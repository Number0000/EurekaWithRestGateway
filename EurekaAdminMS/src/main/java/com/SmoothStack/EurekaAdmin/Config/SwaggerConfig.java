package com.SmoothStack.EurekaAdmin.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2).groupName("LMS Admin MS").apiInfo(apiInfo()).select()
          .paths(regex("/lms/admin.*")).build();                                         
    }
    
	private ApiInfo apiInfo() {
    	return new ApiInfoBuilder().title("LMS Admin MS").version("2.0").build();
    }
}