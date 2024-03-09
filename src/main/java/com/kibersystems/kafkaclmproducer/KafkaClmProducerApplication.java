package com.kibersystems.kafkaclmproducer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaClmProducerApplication implements CommandLineRunner {

	@Value("${info.app.version:none}")
	private String appVersion;

	@Value("${info.app.name:none}")
	private String appName;

	Logger logger = LoggerFactory.getLogger(KafkaClmProducerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(KafkaClmProducerApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${info.application.version:none}") String appVersion) {
		return new OpenAPI().info(new Info()
				.title("API .")
				.version(appVersion)
				.description("Обмен между ЕФС(Siebel) и ИСЖ." +
						"a library for OpenAPI 3 with spring boot.")
				.termsOfService("../")
				.license(new License().name("Free SoftWare Foundation")
						.url("https://en.wikipedia.org/wiki/Free_Software_Foundation")));
	}

	@Override
	public void run(String... args) {
		logger.info("+-----------------------------------------------------------------------------------------------------------+");
		logger.info(" Created by 08.03.2024   : Author: Lyapustin A.S.");
		logger.info("-------------------------------------------------------------------------------------------------------------");
		logger.info("| Application Name       :{}", appName);
		logger.info("| Current version        :{}", appVersion);
		logger.info("=------------------------------------------------------------------------------------------------------------=");


	}
}
