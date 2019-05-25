package com.world.pay.offer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Loading the Swagger bean for mapping and scanning the APIs 
	 * in base package and base path
	 * @return
	 */
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors
                .basePackage("com.world.pay.offer.controller"))
            .paths(PathSelectors.regex("/world/pay/v1/.*"))
            .build().apiInfo(this.apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Offer API Platform Apis")
            .description("APIs to Create and Manage Offers")
            .build();
    }

}
