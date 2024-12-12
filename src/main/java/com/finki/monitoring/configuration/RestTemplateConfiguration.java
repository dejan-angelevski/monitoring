package com.finki.monitoring.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

	@Bean
	public RestTemplate restTemplateOutsideJoke(final RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.rootUri("http://official-joke-api.appspot.com").build();
	}

	@Bean
	public RestTemplate restTemplateInsideJoke(final RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.rootUri("http://localhost:8080").build();
	}

}
