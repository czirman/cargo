package com.cargo.config;

import com.cargo.services.WatchService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppConfig {

   /* @Value("${main.test}")
    private  String test;*/

    @Bean
    public WatchService testServices(){
        return new WatchService("testLombok");
    }
}
