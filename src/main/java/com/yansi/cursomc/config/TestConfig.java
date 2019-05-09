package com.yansi.cursomc.config;

import com.yansi.cursomc.services.EmailService;
import com.yansi.cursomc.services.MockEmailService;
import com.yansi.cursomc.services.test.DBServices;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServices dBServices;

    @Bean
    public boolean instantiateTestDatabase() throws ParseException {

        dBServices.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
