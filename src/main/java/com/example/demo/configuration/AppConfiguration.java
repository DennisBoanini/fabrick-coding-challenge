package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfiguration {

	@Value("${api.sandbox.base_url}")
	private String baseUrl;

	@Value("${api.sandbox.auth_schema}")
	private String authSchema;

	@Value("${api.sandbox.api_key}")
	private String apiKey;

	@Bean
	public WebClient restTemplate() {
		return WebClient.builder()
				.baseUrl(this.baseUrl)
				.defaultHeader("Auth-Schema", this.authSchema)
				.defaultHeader("apikey", this.apiKey)
				.build();
	}
}
