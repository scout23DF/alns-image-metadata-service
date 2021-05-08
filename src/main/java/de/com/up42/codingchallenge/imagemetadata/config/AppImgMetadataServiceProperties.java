package de.com.up42.codingchallenge.imagemetadata.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "imgmetadata-service.config")
public class AppImgMetadataServiceProperties {

    private boolean enabled;

    private String swaggerPageTitle;
    private String swaggerPageDescription;
    private String swaggerPageTermsOfService;
    private String swaggerPageAPIVersion;

    private String sourceDataFilename;

    public boolean generateEasterEggEnabled;

}
