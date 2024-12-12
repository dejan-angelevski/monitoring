package com.finki.monitoring.application.scheduled;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

import com.finki.monitoring.application.usecase.Flow1UseCase;
import com.finki.monitoring.application.usecase.Flow2UseCase;
import com.finki.monitoring.application.usecase.GetAnInsideJokeUseCase;
import com.finki.monitoring.application.usecase.GetAnOutsideJokeUseCase;
import com.finki.monitoring.domain.enumeration.Source;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledExecutions {

	public static final int SECOND = 1000;

	private final Flow1UseCase flow1UseCase;

	private final Flow2UseCase flow2UseCase;

	private final GetAnOutsideJokeUseCase getAnOutsideJokeUseCase;

	private final GetAnInsideJokeUseCase getAnInsideJokeUseCase;

	private final MeterRegistry meterRegistry;

	@Scheduled(fixedRate = 5 * SECOND)
	public void triggerProcessingOnFlow1() {
		Random random = new Random();
		int i = random.nextInt(15, 30);
		for (int j = 0; j < i; j++) {
			this.flow1UseCase.processFlow1();
		}
	}

	@Scheduled(fixedRate = 15 * SECOND)
	public void triggerProcessingOnFlow2() {
		Random random = new Random();
		int i = random.nextInt(1, 5);
		for (int j = 0; j < i; j++) {
			this.flow2UseCase.processFlow2();
		}
	}

	@Scheduled(fixedRate = 30 * SECOND)
	public void triggerGettingAJoke() {
		Random random = new Random();
		for (int i = 0; i < random.nextInt(2); i++) {
			this.getAnOutsideJokeUseCase.getARandomJoke();
		}
		for (int i = 0; i < random.nextInt(20); i++) {
			this.getAnInsideJokeUseCase.getARandomJoke();
		}
	}

	@Scheduled(fixedRate = 5 * SECOND)
	public void triggerActiveUsersMetricPublish() {
		Gauge.builder("currently_active_users", () -> new Random().nextInt(1000, 100000)).register(meterRegistry);
	}

	@Scheduled(fixedRate = 30 * SECOND)
	public void triggerUsersWonJackpotMetricPublish() {
		if (new Random().nextBoolean()) {
			Counter.builder("users_won_jackpot_total").register(meterRegistry).increment();
		}
	}

	@Scheduled(fixedRate = 30 * SECOND)
	public void triggerBackgroundProcessingMetric() {
		final Random random = new Random();
		for (int i = 0; i < random.nextInt(5); i++) {
			Timer backgroundProcessingTimer = Timer.builder("background_task_processing_seconds")
				.tags(List.of(Tag.of("flow", "specificFlow"),
						Tag.of("source", Source.SOURCE1.getId().toString())))
				.register(meterRegistry);
			backgroundProcessingTimer.record(Duration.of(random.nextInt(0, 300), ChronoUnit.SECONDS));
		}
		for (int i = 0; i < random.nextInt(10); i++) {
			Timer backgroundProcessingTimer = Timer.builder("background_task_processing_seconds")
					.tags(List.of(Tag.of("flow", "specificFlow"),
							Tag.of("source", Source.SOURCE2.getId().toString())))
					.register(meterRegistry);
			backgroundProcessingTimer.record(Duration.of(random.nextInt(200, 400), ChronoUnit.SECONDS));
		}
		for (int i = 0; i < random.nextInt(15); i++) {
			Timer backgroundProcessingTimer = Timer.builder("background_task_processing_seconds")
					.tags(List.of(Tag.of("flow", "specificFlow"),
							Tag.of("source", Source.SOURCE3.getId().toString())))
					.register(meterRegistry);
			backgroundProcessingTimer.record(Duration.of(random.nextInt(0, 200), ChronoUnit.SECONDS));
		}
	}

}
