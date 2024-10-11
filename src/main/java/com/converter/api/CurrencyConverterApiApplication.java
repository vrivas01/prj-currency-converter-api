package com.converter.api;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@Slf4j
@SpringBootApplication
@EnableWebFlux
public class CurrencyConverterApiApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApiApplication.class, args);
    }

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("/db/schema.sql")));
        return initializer;
    }

    @Override
    public void run(String... args) {
        serverInfo();
    }

    private void serverInfo() {
        Environment env = this.appContext.getEnvironment();
        String protocol = "http";
        String port = env.getProperty("server.port");
        log.info("\n--------------------------------------------------------\n\tAPI '{}' is running! \n\tLocal: \t\t{}://localhost:{}\n\tProfile(s): \t{}\n--------------------------------------------------------", new Object[]{env.getProperty("spring.application.name"), protocol, port, env.getActiveProfiles()});
    }
}