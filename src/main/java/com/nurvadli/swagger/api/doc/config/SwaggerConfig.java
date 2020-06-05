package com.nurvadli.swagger.api.doc.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Path;


/**
 * @author Nurvadli
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_INFO_TITLE = "Customer Management APIs";

    private static final String API_INFO_DEDCRIPTION = "This page lists all the REST APIs for Customer Management App.";

    private static final String API_INFO_VERSION = "1.0-SNAPSHOT";

    private static final String PATH_REGEX_ALLOWED = "/customer.*";

    private static final String PATH_REGEX_BLOCKED = "/error.*";

    @Bean
    public Docket produceAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nurvadli.swagger.api.doc"))
                .paths(paths())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_INFO_TITLE)
                .description(API_INFO_DEDCRIPTION)
                .version(API_INFO_VERSION)
                .build();
    }

    /**
     * Predicate
     * TODO: Only select APIs that matches the given Predicates
     */
    private Predicate<String> paths() {
        return Predicates.and(PathSelectors.regex(PATH_REGEX_ALLOWED), Predicates.not(PathSelectors.regex(PATH_REGEX_BLOCKED)));
    }
}
