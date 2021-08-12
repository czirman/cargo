package org.czirman.conf;

import org.czirman.services.TestServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppConfig {

   /* @Value("${main.test}")
    private  String test;*/

    @Bean
    public TestServices testServices(){
        return new TestServices("testLombok");
    }
}
