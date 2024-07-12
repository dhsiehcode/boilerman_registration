package com.boilerman.signupV3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean

    CommandLineRunner initDatabase(CollegiateRepository collegiateRepository, AgeGroupRepository ageGroupRepository, RelayRepository relayRepository) {
        return args -> {};
    }
}
