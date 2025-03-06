package com.mcmasters.webscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebScraperApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WebScraperApplication.class, args);
	}

}
