package com.finki.monitoring.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GetAnOutsideJokeUseCase {

	private final RestTemplate restTemplateOutsideJoke;

	@SneakyThrows
	public String getARandomJoke() {
		ResponseEntity<String> responseEntity = restTemplateOutsideJoke.exchange("/random_joke", HttpMethod.GET, null,
				String.class);
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Thread.sleep(new Random().nextInt(300));
			return responseEntity.toString();
		}
		else {
			throw new RuntimeException("Something went wrong with getting outside joke");
		}
	}

}
