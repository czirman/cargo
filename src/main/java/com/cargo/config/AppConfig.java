package com.cargo.config;

import com.cargo.repositories.WatchRepository;
import com.cargo.services.WatchService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
//@AllArgsConstructor /*TODO why don't work */
public class AppConfig {

   /* @Value("${main.test}")
    private  String test;*/

    private final WatchRepository watchRepository;

    public AppConfig(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }


    @Bean
    public WatchService testServices() {
        return new WatchService(watchRepository);
    }
}
