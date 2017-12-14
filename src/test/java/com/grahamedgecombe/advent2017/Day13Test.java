package com.grahamedgecombe.advent2017;

import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class Day13Test {
	private static final Map<Integer, Integer> RANGES = Map.of(
		0, 3,
		1, 2,
		4, 4,
		6, 4
	);

	@Test
	public void testPart1() {
		assertEquals(24, Day13.getSeverity(RANGES));
	}

	@Test
	public void testPart2() {
		assertEquals(10, Day13.getDelay(RANGES));
	}
}
