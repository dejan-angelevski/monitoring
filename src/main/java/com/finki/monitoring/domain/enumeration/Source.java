package com.finki.monitoring.domain.enumeration;

import static java.lang.String.format;

import lombok.Getter;

@Getter
public enum Source {

	SOURCE1(1), SOURCE2(2), SOURCE3(3);

	private final Integer id;

	Source(Integer i) {
		this.id = i;
	}

	public static Source fromSourceId(final Integer sourceId) {
		for (final Source os : Source.values()) {
			if (os.id.equals(sourceId)) {
				return os;
			}
		}
		throw new RuntimeException(format("Source id %s not supported", sourceId));
	}

}
