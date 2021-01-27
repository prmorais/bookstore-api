package com.paulo.bookstore.config;

import com.paulo.bookstore.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    @Autowired
    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public void instanciaBaseDeDados() {
        dbService.instanciaBaseDeDados();
    }
}
