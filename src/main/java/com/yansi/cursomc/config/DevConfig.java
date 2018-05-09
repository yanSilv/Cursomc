package com.yansi.cursomc.config;

import com.yansi.cursomc.services.test.DBServices;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBServices dBServices;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateTestDatabase() throws ParseException {

        if (!"create".equals(strategy)) {
            return false;
        }
        dBServices.instantiateTestDatabase();

        return true;
    }
}
