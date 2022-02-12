/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jumia.services.exercise.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Nour_Mahmoud
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.jumia.services.exercise"))
                .paths(PathSelectors.any()).build().apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Jumia Services Phone Task",
                "Sample APIs for a Task that list and categorize country phone numbers.",
                "1.0", "Free to use", new Contact("Nour Khaled", null, "nourkembal@gmail.com"),
                null, null, Collections.emptyList());
    }

}
