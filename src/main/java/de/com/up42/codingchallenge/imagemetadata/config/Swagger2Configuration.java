package de.com.up42.codingchallenge.imagemetadata.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @Profile({"!prod && swagger"})
public class Swagger2Configuration implements WebMvcConfigurer {

    private final AppImgMetadataServiceProperties appKitchenServiceProperties;

    @Autowired
    public Swagger2Configuration(AppImgMetadataServiceProperties pAppKitchenServiceProperties) {
        this.appKitchenServiceProperties = pAppKitchenServiceProperties;
    }


    @Bean
    public OpenAPI customOpenAPI() {

        // ModelConverters.getInstance().addConverter(new WebFluxSupportConverter());

        return new OpenAPI().components(new Components().addSecuritySchemes("JWE",

                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWE")))
                                    .info(new Info().title(this.appKitchenServiceProperties.getSwaggerPageTitle())
                                    .version(this.appKitchenServiceProperties.getSwaggerPageAPIVersion()));
    }


    @Bean
    public GroupedOpenApi groupOpenApi() {
        String paths[] = {"/api/**"};
        String packagesToscan[] = {"de.com.up42.codingchallenge.imagemetadata"};
        return GroupedOpenApi.builder()
                .group("UP42-ImageMetadataService-API's")
                .pathsToMatch(paths)
                .packagesToScan(packagesToscan)
                .build();
    }

}