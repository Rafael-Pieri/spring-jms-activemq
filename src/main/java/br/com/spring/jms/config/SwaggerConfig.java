package br.com.spring.jms.config;

import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationId;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket apiDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName(applicationId)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(paths())
            .build();
    }

    private Predicate<String> paths() {
        return regex("/api.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).description(description)
            .contact(new Contact(contactName, null, null))
            .version(version)
            .build();
    }
}