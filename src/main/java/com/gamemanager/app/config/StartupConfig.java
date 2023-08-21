package com.gamemanager.app.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.gamemanager.app.services.PublisherService;

@Configuration
public class StartupConfig {

	@Autowired
	PublisherService publisherService;


    @Bean
    CommandLineRunner initialize() {

        return args -> {

        	publisherService.NotifyPublisherManager();

        };
    }
}