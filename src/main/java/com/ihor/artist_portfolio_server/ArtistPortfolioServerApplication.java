package com.ihor.artist_portfolio_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ArtistPortfolioServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtistPortfolioServerApplication.class, args);
	}

}
