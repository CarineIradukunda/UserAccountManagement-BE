package com.user.management.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSource {

    @Primary
    @Bean(name = "users_data")
    @ConfigurationProperties("connection")
    DataSource sql(){
        return (DataSource) DataSourceBuilder.create().build();
    }
}
