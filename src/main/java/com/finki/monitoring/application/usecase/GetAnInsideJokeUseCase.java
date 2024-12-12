package com.finki.monitoring.application.usecase;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@Slf4j
public class GetAnInsideJokeUseCase {

	private final RestTemplate restTemplate;

	public GetAnInsideJokeUseCase(@Qualifier("restTemplateInsideJoke") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@SneakyThrows
	public String getARandomJoke() {
		ResponseEntity<String> responseEntity = this.restTemplate.exchange("/joke", HttpMethod.GET, null, String.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Thread.sleep(new Random().nextInt(100));
			return responseEntity.getBody();
		}
		else {
			throw new RuntimeException("Something went wrong with getting inside joke");
		}
	}

}
