package com.finki.monitoring.application.usecase;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Flow1UseCase {

	@SneakyThrows
	@Async("customThreadPoolTaskExecutorFlow1")
	public void processFlow1() {
		log.info("processing flow 1");
		Thread.sleep(new Random().nextInt(50, 1000));
	}

}
