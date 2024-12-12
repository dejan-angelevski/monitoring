package com.finki.monitoring.application.usecase;

import java.util.Random;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Flow2UseCase {

	@SneakyThrows
	@Async("customThreadPoolTaskExecutorFlow2")
	public void processFlow2() {
		log.info("processing flow 2");

		Thread.sleep(new Random().nextInt(10, 20));
	}

}
