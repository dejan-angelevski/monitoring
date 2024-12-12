package com.finki.monitoring.configuration;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class CustomThreadPoolConfiguration {

	@Bean("customThreadPoolTaskExecutorFlow1")
	public ThreadPoolTaskExecutor flow1ThreadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutorBuilder().maxPoolSize(10)
			.corePoolSize(10)
			.threadNamePrefix("processor 1")
			.build();
	}

	@Bean("customThreadPoolTaskExecutorFlow2")
	public ThreadPoolTaskExecutor flow2ThreadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutorBuilder().maxPoolSize(5)
			.corePoolSize(5)
			.threadNamePrefix("processor 2")
			.build();
	}

}
