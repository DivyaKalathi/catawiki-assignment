package com.catawiki.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Config class stores the details from application properties.
 * Using @Data of lombok makes ot easy to set and get the values of each property
 */
@Data
@Component
@ConfigurationProperties
@Configuration
@ComponentScan(basePackages = {"com.catawiki"})
@ConfigurationPropertiesScan(basePackages = {"com.catawiki.config"})
public class Config {
    private String url;
    private int explicitWait;
    private int implicitWait;
    private int pageLoadTimeoutWait;

}

