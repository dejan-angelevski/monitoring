package com.finki.monitoring.configuration;

import java.util.Random;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class MyHealthMetricsExportConfiguration {

	public MyHealthMetricsExportConfiguration(MeterRegistry registry, HealthEndpoint healthEndpoint) {
		Gauge.builder("health", healthEndpoint, this::getStatusCode).strongReference(true).register(registry);
	}

	private int getStatusCode(HealthEndpoint health) {
		/*
		 * Status status = health.health().getStatus(); if (Status.UP.equals(status)) {
		 * return 1; } if (Status.OUT_OF_SERVICE.equals(status)) { return -2; } if
		 * (Status.DOWN.equals(status)) { return -1; } return 0;
		 */
		Random random = new Random();
		final int randomInt = random.nextInt(-1, 2);
		switch (randomInt) {
			case 1 -> {
				for (int i = 0; i < random.nextInt(100); i++) {
					log.info("STATUS OK");
				}
            }
			case 0 -> {
				for (int i = 0; i < random.nextInt(50); i++) {
					log.warn("STATUS UNKNOWN");
				}
            }
			case -1 -> {
				for (int i = 0; i < random.nextInt(10); i++) {
					log.error("STATUS DOWN");
				}
            }
			default -> throw new RuntimeException("unknown health status!");
		}
		return randomInt;
	}

}
