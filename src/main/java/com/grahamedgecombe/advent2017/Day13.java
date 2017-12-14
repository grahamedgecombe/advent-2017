package com.grahamedgecombe.advent2017;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public final class Day13 {
	public static void main(String[] args) throws IOException {
		Map<Integer, Integer> ranges = AdventUtils.readLines("day13.txt")
			.stream()
			.map(line -> line.split(": "))
			.collect(Collectors.toMap(parts -> Integer.parseInt(parts[0]), parts -> Integer.parseInt(parts[1])));
		System.out.println(getSeverity(ranges));
		System.out.println(getDelay(ranges));
	}

	private static int getSeverity(Map<Integer, Integer> ranges, int delay, boolean part1) {
		int severity = 0;

		for (Map.Entry<Integer, Integer> entry : ranges.entrySet()) {
			int depth = entry.getKey();
			int range = entry.getValue();

			int time = delay + depth;
			if (time % ((range - 1) * 2) == 0) {
				if (part1) {
					severity += depth * range;
				} else {
					return 1;
				}
			}
		}

		return severity;
	}

	public static int getSeverity(Map<Integer, Integer> ranges) {
		return getSeverity(ranges, 0, true);
	}

	public static int getDelay(Map<Integer, Integer> ranges) {
		int delay = 0;
		while (getSeverity(ranges, delay, false) != 0) {
			delay++;
		}
		return delay;
	}
}
